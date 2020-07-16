package com.group18.asdc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.group18.asdc.SystemConfig;
import com.group18.asdc.dao.IPasswordPolicyDB;
import com.group18.asdc.entities.Course;
import com.group18.asdc.service.AdminService;

@Controller
public class AdminController {

	private static final AdminService theAdminService = SystemConfig.getSingletonInstance().getServiceAbstractFactory()
			.getAdminService();

	@GetMapping("/adminhome")
	public String adminHome() {
		return "adminhome";
	}

	@GetMapping("/adminadd")
	public String adminAddDisplay(Model model) {
		model.addAttribute("course", new Course());
		return "adminaddcourse";
	}

	@PostMapping("/adminadd")
	public String adminAddForm(@ModelAttribute("course") Course course, BindingResult bindingresult) {
		if (bindingresult.hasErrors()) {
			return "redirect:/adminadd?error";
		}
		boolean result = theAdminService.createCourse(course);
		if (result) {
			return "adminaddcourseresult";
		} else {
			return "redirect:/adminadd?error";
		}
	}

	@GetMapping("/admindelete")
	public String adminDeleteDisplay(Model model) {
		model.addAttribute("course", new Course());
		return "admindeletecourse";
	}

	@PostMapping("/admindelete")
	public String adminDeleteForm(@ModelAttribute("course") Course course, BindingResult bindingresult,
			Model theModel) {
		if (bindingresult.hasErrors()) {
			return "redirect:/admindelete?error";
		} else {
			boolean result = theAdminService.deleteCourse(course);
			if (result) {
				return "admindeletecourseresult";
			} else {
				return "redirect:/admindelete?error";
			}
		}
	}

	@GetMapping("/resetPasswordPolicies")
	public String resetPasswordPolicies() {
		IPasswordPolicyDB passwordPolicyDB = SystemConfig.getSingletonInstance().getDaoAbstractFactory()
				.getPasswordPolicyDB();
		SystemConfig.getSingletonInstance().setBasePasswordPolicyManager(passwordPolicyDB);
		SystemConfig.getSingletonInstance().setPasswordPolicyManager(passwordPolicyDB);
		return "policyReset";
	}
}