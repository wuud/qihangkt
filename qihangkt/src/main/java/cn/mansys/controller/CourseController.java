package cn.mansys.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.mansys.authority.AuthClass;
import cn.mansys.authority.AuthMethod;
import cn.mansys.model.Course;
import cn.mansys.model.HostHolder;
import cn.mansys.model.User;
import cn.mansys.model.Video;
import cn.mansys.service.CourseService;
import cn.mansys.service.JoinCourseService;
import cn.mansys.service.UserService;
import cn.mansys.service.VideoService;
import cn.mansys.utils.Constants;

@AuthClass
@Controller
public class CourseController {

	@Autowired
	CourseService courseService;
	@Autowired
	UserService userService;
	@Autowired
	VideoService videoService;
	@Autowired
	JoinCourseService joinCourseService;
	@Autowired
	HostHolder hostHolder;

	@RequestMapping(value = "/course/{courseId}")
	public String courseDetail(Model model, @PathVariable("courseId") int courseId) {
		int userId=hostHolder.getUser().getId();
		Course course = courseService.getCourseById(courseId);
		model.addAttribute("course", course);
		boolean isJoin = joinCourseService.isJoin(courseId, userId);
		model.addAttribute("isJoin",isJoin);
		return "courseDetail";
	}
	@RequestMapping(value = "/allCourse")
	public String allCourse(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
		List<Course> courseList = courseService.getCourseByPage(page, Constants.COURSE_PAGE_SIZE);

		userService.pageControl(courseList.size(), model, page, Constants.COURSE_PAGE_SIZE);

		model.addAttribute("courseList", courseList);
		return "allCourse";
	}

	/**
	 * 课程上传
	 * @param picture 课程封面
	 * @param file	课程资料
	 * @param videos 课程视频
	 * @param userName 课程发布者
	 * @param courseName	课程名称
	 * @param courseIntro	课程介绍
	 * @return
	 */
	@AuthMethod(grade=2)
	@RequestMapping(value = "/addCourse", method = RequestMethod.POST)
	public String addCourse(@RequestParam(value = "coursePicture") MultipartFile picture,
			@RequestParam(value = "courseFile", required = false) MultipartFile file,
			@RequestParam("courseVideo") MultipartFile[] videos, 
			@RequestParam("courseUser") String userName,
			@RequestParam("courseName") String courseName, 
			@RequestParam("courseIntro") String courseIntro,
			HttpServletRequest request) {
		String picturePath = null;
		String courseFile = null;
		String basePath=request.getSession().getServletContext().getRealPath("");
		try {
			if (picture!=null) {
				String filename = picture.getOriginalFilename();
				String path = new String(basePath+Constants.UPLOAD_PATH + courseName + "\\picture");
				File uploadfile = new File(path, filename);
				if (!uploadfile.exists()) {
					uploadfile.mkdirs();
				}
				picture.transferTo(uploadfile);
				//上传文件要上传到绝对路径，但数据库中要存储相对路径
				String pictureRealPath = uploadfile.getPath();
				picturePath=pictureRealPath.substring(pictureRealPath.indexOf("static"));
			}
			if (file != null) {

				String filename = file.getOriginalFilename();
				String path = new String(basePath+Constants.UPLOAD_PATH + courseName + "\\file");
				File uploadfile = new File(path, filename);
				if (!uploadfile.exists()) {
					uploadfile.mkdirs();
				}
				file.transferTo(uploadfile);// 将上传文件保存到一个目录文件中
				String courseFileRealPath = uploadfile.getPath();
				courseFile=courseFileRealPath.substring(courseFileRealPath.indexOf("static"));
			}

			User user = userService.getUserByName(userName);
			Course course = new Course(courseName, user, courseIntro, picturePath, courseFile);
			courseService.insertCourse(course);
			
			if (videos != null) {
				for (MultipartFile video : videos) {
					String filename = video.getOriginalFilename();
					String path = new String(basePath+Constants.UPLOAD_PATH + courseName + "\\video");
					File uploadfile = new File(path, filename);
					if (!uploadfile.exists()) {
						uploadfile.mkdirs();
					}
					long videoSize = video.getSize();
					Double videoSizeM=videoSize/(1024*1024.0);
					video.transferTo(uploadfile);// 将上传文件保存到一个目录文件中

					Course courseByName = courseService.getCourseByName(courseName);
					String videoRealPath=uploadfile.getPath();
					String videoPath=videoRealPath.substring(videoRealPath.indexOf("static"));
					Video v = new Video(filename, courseByName, videoPath,videoSizeM);
					videoService.addVideo(v);

				}
			}
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}
	
	@AuthMethod(grade=2)
	@RequestMapping(value = "/addCourse", method = RequestMethod.GET)
	public String addCoursePage(Model model) {
		return "addCourse";
	}
	
	/**
	 * 文件下载
	 */
	@RequestMapping(value="/course/{courseId}/download")
	public ResponseEntity<byte[]> downCourseFile(@PathVariable("courseId")int courseId) throws IOException {
		//要下载的文件所在目录的绝对路径
        Course course = courseService.getCourseById(courseId);
        String filePath=Constants.DOWNLOAD_PATH + course.getCourseFile();
        File file=new File(filePath);
        HttpHeaders hh=new HttpHeaders();
        //解决中文乱码问题
        String downloadFilename=new String(course.getCourseFile().getBytes("utf-8"),"iso-8859-1");
        // 通知浏览器下载方式为attachment
        hh.setContentDispositionFormData("attachment", downloadFilename);
        //application/octet-stream二进制流数据下载
        hh.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),hh,HttpStatus.CREATED);
	}

}
