package com.example.client.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.ModelMap;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.example.client.model.Pernikahan;
import com.example.client.service.AdminPernikahanService;

@Controller
public class AdminPernikahanController {

	@Autowired
	private AdminPernikahanService service; 
	
	@RequestMapping(value = "/adminsave_pernikahan", method = RequestMethod.POST)
	public String savePernikahan(@ModelAttribute("pernikahan") Pernikahan pernikahan) {
		service.save(pernikahan);
		
		return "redirect:/admin/pernikahan.html";
	}

	@RequestMapping("/admin/pernikahan.html")
	public String viewDaftarPernikahan(Model model) {
		List listpernikahan = service.listAll();
		model.addAttribute("listpernikahan", listpernikahan);
		
		return "/admin/pernikahan";
	}

    
	@RequestMapping("/admin/edit_pernikahan.html/{pernikahan_id}")
	public ModelAndView showEditPernikahanPage(@PathVariable(name = "pernikahan_id") int pernikahan_id) {
		ModelAndView mav = new ModelAndView("/admin/edit_pernikahan.html");
		Pernikahan pernikahan = service.get(pernikahan_id);
		mav.addObject("pernikahan", pernikahan);
		return mav;
    }
}