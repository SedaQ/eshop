package com.fi.ls.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Pavel Šeda (441048)
 *
 */
@Controller
public class HomeController {

	@RequestMapping(value = "/register")
	public String register(Model model) {
		return "register";
	}

}
