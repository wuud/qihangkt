package cn.mansys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mansys.model.Role;
import cn.mansys.model.User;
import cn.mansys.service.RoleService;
import cn.mansys.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;

	@RequestMapping(value = "/admin/searchUser", method = RequestMethod.POST)
	public String searchUser(String userInfo, Model model, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int pageSize) {
		List<User> userList = userService.searchUser(userInfo, pageSize, page);
		List<Role> roleList = roleService.getAllRole();
		model.addAttribute("roleList", roleList);
		// ����ҳ��ѯ�ĵ������ݴ���ǰ��
		model.addAttribute("userList", userList);
		// ����û�����,��ǰ����ʾ
		Integer total = userList.size();
		// �����ҳ��
		int totalPages = (int) (((double) total / pageSize) + 1);
		// �����һҳ
		int prevPage = 0;
		if (page > 0) {
			prevPage = page - 1;
		}
		// �����һҳ
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
				+ "					<h4 class=\"modal-title\" id=\"myModalLabel\">�༭�û�</h4>\r\n"
				+ "				</div>\r\n" + "				<div class=\"modal-body\">\r\n"
				+ "					<form id=\"updateUserForm\"\r\n" + "						action=\"" + path
				+ "/admin/updateUser\"\r\n" + "						method=\"post\">\r\n"
				+ "                       <input type='hidden' name='id' value='" + u.getId() + "'/>"
				+ "						<div class=\"form-group\">\r\n"
				+ "							<label>�û�����</label> <input type=\"text\" name=\"username\"\r\n"
				+ "								class=\"form-control\" value=\"" + u.getUsername() + "\">\r\n"
				+ "						</div>\r\n" + "						<div class=\"form-group\">\r\n"
				+ "							<label>���루���ձ�ʾ���޸ģ���</label> <input type=\"password\" name=\"password\"\r\n"
				+ "								class=\"form-control\" placeholder=\"�����Ѽ���\">\r\n"
				+ "						</div>\r\n" + "						<div class=\"form-group\">\r\n"
				+ "							<label>�ֻ��ţ�</label> <input type=\"text\" name=\"phone\"\r\n"
				+ "								class=\"form-control\" value=\"" + u.getPhone() + "\">\r\n"
				+ "						</div>\r\n" + "						<div class=\"form-group\">\r\n"
				+ "							<label>���䣺</label> <input type=\"text\" name=\"email\"\r\n"
				+ "								class=\"form-control\" value=\"" + u.getEmail() + "\">\r\n"
				+ "						</div>\r\n" + "						<div class=\"form-group\">\r\n"
				+ "							<label>�û����ͣ�</label> <select class=\"selectpicker form-control\"\r\n"
				+ "								name=\"role_Id\">\r\n" + optStr
				+ "							</select>\r\n" + "						</div>\r\n"
				+ "					</form>\r\n" + "				</div>\r\n"
				+ "				<div class=\"modal-footer\">\r\n"
				+ "					<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">�ر�</button>\r\n"
				+ "					<button onclick=\"updateUserFromSubmit()\" type=\"button\" class=\"btn btn-primary\">���</button>\r\n"
				+ "				</div>\r\n" + "			</div>\r\n" + "		</div>";
	}

}
