package cn.mansys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mansys.model.Course;
import cn.mansys.model.HostHolder;
import cn.mansys.model.Role;
import cn.mansys.model.User;
import cn.mansys.service.CourseService;
import cn.mansys.service.JoinCourseService;
import cn.mansys.service.RoleService;
import cn.mansys.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	@Autowired
	HostHolder hostHolder;
	@Autowired
	CourseService courseService;
	@Autowired
	JoinCourseService joinCourseService;

	@RequestMapping(value = "/localUser")
	public String getUserDetail(Model model) {
		int userId = hostHolder.getUser().getId();
		// 我参加的课程
		List<Integer> userCourses = joinCourseService.getUserCourses(userId);
		List<Map<String, Object>> joinCourseList = new ArrayList<>();
		if (userCourses != null) {
			for (Integer i : userCourses) {
				Map<String, Object> map = new HashMap<>();
				Course course = courseService.getCourseById(i);
				System.out.println(course.getUser().getUsername());
				map.put("course", course);
				map.put("teacher", course.getUser().getUsername());
				joinCourseList.add(map);
			}
		}
		model.addAttribute("joinCourseList", joinCourseList);

		if (hostHolder.getUser().getRoleId().getId() >= 2) {
			// 我发布的课程
			List<Course> courseByUser = courseService.getCourseByUser(userId);
			List<Map<String, Object>> myCourseList = new ArrayList<>();
			for (Course c : courseByUser) {
				Map<String, Object> map = new HashMap<>();
				map.put("course", c);
				map.put("count", joinCourseService.countCourses(c.getId()));
				myCourseList.add(map);
			}
			model.addAttribute("myCourseList", myCourseList);
		}
		return "userDetail";
	}

	@RequestMapping(value = "/admin/searchUser", method = RequestMethod.POST)
	public String searchUser(String userInfo, Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int pageSize) {
		List<User> userList = userService.searchUser(userInfo, pageSize, page);
		List<Role> roleList = roleService.getAllRole();
		model.addAttribute("roleList", roleList);
		// 将分页查询的到的数据传给前端
		model.addAttribute("userList", userList);
		// 获得用户总量,在前端显示
		Integer total = userList.size();
		// 获得总页数
		int totalPages = (int) (((double) total / pageSize) + 1);
		// 获得上一页
		int prevPage = 0;
		if (page > 0) {
			prevPage = page - 1;
		}
		// 获得下一页
		int nextPage = totalPages;
		if (page < totalPages) {
			nextPage = page + 1;
		}

		model.addAttribute("total", total);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("page", page);
		model.addAttribute("prevPage", prevPage);
		model.addAttribute("nextPage", nextPage);
		return "admin/user";
	}

	@RequestMapping(value = "/admin/addUser", method = RequestMethod.POST)
	public String addUser(User user, int role_Id, Model model) {
		user.setRoleId(roleService.getRoleById(role_Id));
		userService.insertUser(user);
		return "redirect:/admin/user";
	}

	@RequestMapping(value = "/admin/delUser", method = RequestMethod.GET)
	public String delUser(int id) {
		userService.delUser(id);
		return "redirect:/admin/user";
	}

	@ResponseBody
	@RequestMapping(value = "admin/batchDelUsers", method = RequestMethod.POST)
	public String batchDelUsers(String uid) {

		uid = uid.substring(1, uid.length() - 1).replace("\"", "");
		String[] idsStr = uid.split(",");
		int[] ids = new int[idsStr.length];
		for (int i = 0; i < idsStr.length; i++) {
			ids[i] = Integer.valueOf(idsStr[i]);
		}
		userService.batchDelUsers(ids);
		return "success";
	}

	@RequestMapping(value = "/admin/updateUser", method = RequestMethod.POST)
	public String updateUser(User user, int role_Id) {
		user.setRoleId(roleService.getRoleById(role_Id));
		userService.updateUser(user);
		return "redirect:/admin/user";
	}

	@ResponseBody
	@RequestMapping(value = "/admin/updateUserModal", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String updateUserModal(Integer id, HttpServletRequest request) {
		User u = userService.getUserById(id);
		List<Role> roleList = roleService.getAllRole();
		String optStr = "";
		for (Role r : roleList) {
			if (u.getRoleId().getId() == r.getId()) {
				optStr = optStr + "<option selected value=\"" + r.getId() + "\">" + r.getRname() + "</option>\r\n";
			} else {
				optStr = optStr + "<option value=\"" + r.getId() + "\">" + r.getRname() + "</option>\r\n";
			}
		}
		String path = request.getContextPath();

		return "<div class=\"modal-header\">\r\n"
				+ "					<button type=\"button\" class=\"close\" data-dismiss=\"modal\"\r\n"
				+ "						aria-label=\"Close\">\r\n" + "						<span>&times;</span>\r\n"
				+ "					</button>\r\n"
				+ "					<h4 class=\"modal-title\" id=\"myModalLabel\">编辑用户</h4>\r\n"
				+ "				</div>\r\n" + "				<div class=\"modal-body\">\r\n"
				+ "					<form id=\"updateUserForm\"\r\n" + "						action=\"" + path
				+ "/admin/updateUser\"\r\n" + "						method=\"post\">\r\n"
				+ "                       <input type='hidden' name='id' value='" + u.getId() + "'/>"
				+ "						<div class=\"form-group\">\r\n"
				+ "							<label>用户名：</label> <input type=\"text\" name=\"username\"\r\n"
				+ "								class=\"form-control\" value=\"" + u.getUsername() + "\">\r\n"
				+ "						</div>\r\n" + "						<div class=\"form-group\">\r\n"
				+ "							<label>密码（留空表示不修改）：</label> <input type=\"password\" name=\"password\"\r\n"
				+ "								class=\"form-control\" placeholder=\"密码已加密\">\r\n"
				+ "						</div>\r\n" + "						<div class=\"form-group\">\r\n"
				+ "							<label>手机号：</label> <input type=\"text\" name=\"phone\"\r\n"
				+ "								class=\"form-control\" value=\"" + u.getPhone() + "\">\r\n"
				+ "						</div>\r\n" + "						<div class=\"form-group\">\r\n"
				+ "							<label>邮箱：</label> <input type=\"text\" name=\"email\"\r\n"
				+ "								class=\"form-control\" value=\"" + u.getEmail() + "\">\r\n"
				+ "						</div>\r\n" + "						<div class=\"form-group\">\r\n"
				+ "							<label>用户类型：</label> <select class=\"selectpicker form-control\"\r\n"
				+ "								name=\"role_Id\">\r\n" + optStr
				+ "							</select>\r\n" + "						</div>\r\n"
				+ "					</form>\r\n" + "				</div>\r\n"
				+ "				<div class=\"modal-footer\">\r\n"
				+ "					<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">关闭</button>\r\n"
				+ "					<button onclick=\"updateUserFromSubmit()\" type=\"button\" class=\"btn btn-primary\">完成</button>\r\n"
				+ "				</div>\r\n" + "			</div>\r\n" + "		</div>";
	}

}
