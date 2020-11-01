package com.example.client.controller;

import java.util.HashMap;
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
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.example.client.model.MemberDiklat;
import com.example.client.model.DetailJumlah;

import com.example.client.service.AdminMemberDiklatService;

@Controller
public class AdminMemberDiklatController {
    @Autowired
	private AdminMemberDiklatService service; 
	
	@RequestMapping(value = "/adminsave_memberdiklat", method = RequestMethod.POST)
	public String saveMemberdiklat(@ModelAttribute("memberdiklat") MemberDiklat memberdiklat) {
		service.save(memberdiklat);
		
		return "redirect:/admin/memberdiklat.html";
	}

	@RequestMapping("/admin/memberdiklat.html")
	public String viewDaftarMemberdiklat(Model model) {
		List listmemberdiklat = service.listAll();
		BigInteger jumlah = service.listAllMember();
		model.addAttribute("listmemberdiklat", listmemberdiklat);
		model.addAttribute("jumlah", jumlah);
		return "/admin/memberdiklat";
	}


    
	@RequestMapping("/admin/edit_memberdiklat.html/{memberdiklat_id}")
	public ModelAndView showEditMemberdiklatPage(@PathVariable(name = "memberdiklat_id") int memberdiklat_id) {
		ModelAndView mav = new ModelAndView("/admin/edit_memberdiklat.html");
		MemberDiklat memberdiklat = service.get(memberdiklat_id);
		mav.addObject("memberdiklat", memberdiklat);
		return mav;
    }   
}