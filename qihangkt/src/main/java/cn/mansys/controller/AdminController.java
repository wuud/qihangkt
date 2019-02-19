package cn.mansys.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.mansys.authority.AuthClass;
import cn.mansys.authority.AuthMethod;
import cn.mansys.model.Role;
import cn.mansys.model.User;
import cn.mansys.service.RoleService;
import cn.mansys.service.UserService;

@AuthClass(grade = 3)
@Controller
public class AdminController {

	@Autowired
	RoleService roleService;
	@Autowired
	UserService userService;

	@RequestMapping(value = "/admin")
	public String admin(Model model) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(d);
		model.addAttribute("date", date);
		return "admin/admin";
	}

	@RequestMapping(value = "/admin/user/1", method = RequestMethod.GET)
	public String userManage(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int pageSize) {
		int roleId = 1;
		userService.userPageControl(model, roleId, page, pageSize);
		return "admin/user";

	}
	@RequestMapping(value = "/admin/user/2", method = RequestMethod.GET)
	public String teacherManage(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int pageSize) {
		int roleId = 2;
		userService.userPageControl(model, roleId, page, pageSize);
		return "admin/teacher";
		
	}
	@AuthMethod(grade=4)
	@RequestMapping(value = "/admin/user/3", method = RequestMethod.GET)
	public String managerManage(Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int pageSize) {
		int roleId = 3;
		userService.userPageControl(model, roleId, page, pageSize);
		return "admin/manager";
		
	}

	@RequestMapping(value = "/admin/res")
	public String res() {
		return "admin/res";
	}

	@RequestMapping(value = "/admin/welcome")
	public String welcome() {
		return "admin/welcome";
	}
}
