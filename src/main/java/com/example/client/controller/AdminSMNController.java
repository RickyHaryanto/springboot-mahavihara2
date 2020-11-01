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
import com.example.client.model.SMN;

import com.example.client.model.Notif;
import com.example.client.service.AdminSMNService;
import com.example.client.repository.AdminNotifRepository;

import com.example.client.repository.AdminSMNRepository;
import com.example.client.service.AdminNotifService;

import com.example.client.service.AdminUserService;
import com.example.client.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class AdminSMNController {
    @Autowired
	private AdminSMNService service;
	@Autowired
	private AdminNotifService notifservice;
	@Autowired
	private AdminNotifRepository notifrepo;
	@Autowired
	private AdminSMNRepository repo;
	@Autowired
	private AdminUserService service2;

	int currentID = 0;
	

	@RequestMapping("/admin/list_nilai.html")
	public String viewDaftarNilai(Model model) {
		List listsmn = service.listAll();
		model.addAttribute("listsmn", listsmn);
		currentID=0;
		return "/admin/list_nilai";
	}

    @RequestMapping("/admin/add_SMN.html")
	public String showNewSMNPage(Model model) {
		SMN smn = new SMN();
		model.addAttribute("smn", smn);
		currentID=0;

		
		List daftarumat = repo.daftarumat();
		model.addAttribute("daftarumat", daftarumat);

		return "/admin/add_SMN";
    }
    
	@RequestMapping("/admin/edit_smn.html/{smn_id}")
	public ModelAndView showEditSMNPage(@PathVariable(name = "smn_id") int smn_id) {
		ModelAndView mav = new ModelAndView("/admin/edit_smn.html");
		SMN smn = service.get(smn_id);
		currentID = smn_id;
		mav.addObject("smn", smn);
		return mav;
	}

	SMN smn = new SMN();
	
	@RequestMapping(value = "/adminsave_smn", method = RequestMethod.POST)
	public String saveSMN(RedirectAttributes redirectAttributes, 
	ModelMap modelMap, 
	@RequestParam("user_id") Long user_id, 
	@RequestParam("smn_dharma") String smn_dharma, 
	@RequestParam("smn_etika") String smn_etika,
	@RequestParam("smn_ritual") String smn_ritual,
	@RequestParam("smn_keterangan") String smn_keterangan,
	@RequestParam("smn_tanggal") String smn_tanggal
	) {


		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usr=service2.findByEmail(user.getUsername());

		smn.setSmn_dharma(smn_dharma);
		smn.setSmn_etika(smn_etika);
		smn.setSmn_ritual(smn_ritual);
		smn.setSmn_keterangan(smn_keterangan);
		smn.setSmn_tanggal(smn_tanggal);
		smn.setUser_id(user_id);
		
		service.save(smn,Long.valueOf(currentID));
		
		//Notif
		List lc = notifrepo.notifnilai(user_id);
		for (int i=0;i<lc.size();i++)
        {
			Object[] cr=(Object[])lc.get(i);
			Notif notif = new Notif();
            
			notif.setNotif_isi("Nilai baru telah diUpload");
			notif.setNotif_flag("0");
			notif.setNotif_tanggal(java.time.LocalDate.now()+"");
			notif.setUser_id(Long.valueOf(cr[0]+""));
			notifservice.save(notif);
		}

		//Notif

		return "redirect:/admin/list_nilai.html";
	}
}