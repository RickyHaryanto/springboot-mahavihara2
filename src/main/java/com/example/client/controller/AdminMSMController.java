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
import com.example.client.model.MSM;
import com.example.client.service.AdminMSMService;

@Controller
public class AdminMSMController {
    @Autowired
	private AdminMSMService service; 
	
	@RequestMapping(value = "/adminsave_msm", method = RequestMethod.POST)
	public String saveMSM(@ModelAttribute("msm") MSM msm) {
		service.save(msm);
		
		return "redirect:/admin/anggota_sm.html";
	}

	@RequestMapping("/admin/anggota_sm.html")
	public String viewDaftarMSM(Model model) {
		List listmsm = service.listAll();
		model.addAttribute("listmsm", listmsm);
		
		return "/admin/anggota_sm";
	}

    
	@RequestMapping("/admin/edit_msm.html/{msm_id}")
	public ModelAndView showEditMSMPage(@PathVariable(name = "msm_id") int msm_id) {
		ModelAndView mav = new ModelAndView("/admin/edit_msm.html");
		MSM msm = service.get(msm_id);
		mav.addObject("msm", msm);
		return mav;
    }   
}