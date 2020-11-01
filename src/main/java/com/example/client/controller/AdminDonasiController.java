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
import com.example.client.model.Donasi;
import com.example.client.service.AdminDonasiService;
import com.example.client.service.AdminDonasiUangService;

@Controller
public class AdminDonasiController {
    @Autowired
	private AdminDonasiService service; 

	@Autowired
	private AdminDonasiUangService service2; 

	
	@RequestMapping(value = "/adminsave_donasi", method = RequestMethod.POST)
	public String saveDonasi(@ModelAttribute("donasi") Donasi donasi) {
		service.save(donasi);
		
		return "redirect:/admin/list_donasi.html";
	}

	@RequestMapping("/admin/list_donasi.html")
	public String viewDaftarDonasi(Model model) {
		List listdonasi = service.listAll();
		model.addAttribute("listdonasi", listdonasi);


		List listdonasiuang = service2.listAll();
		model.addAttribute("listdonasiuang", listdonasiuang);
		
		return "/admin/list_donasi";
	}

    @RequestMapping("/admin/donasi.html")
	public String showNewDonasiPage(Model model) {
		Donasi donasi = new Donasi();
		model.addAttribute("donasi", donasi);

		List daftarumat = service.daftar();
		model.addAttribute("daftarumat", daftarumat);

		return "/admin/donasi";
    }
    
	
}