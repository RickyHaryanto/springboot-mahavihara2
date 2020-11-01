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

import com.example.client.repository.AdminNotifRepository;
import com.example.client.service.AdminNotifService;
import com.example.client.model.Notif;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.example.client.model.SMP;
import com.example.client.service.AdminSMPService;

@Controller
public class AdminSMPController {
    @Autowired
	private AdminSMPService service; 
	
@Autowired
private AdminNotifService notifservice;
@Autowired
private AdminNotifRepository notifrepo;

	@RequestMapping(value = "/adminsave_smp", method = RequestMethod.POST)
	public String saveSMP(@ModelAttribute("smp") SMP smp) {
		service.save(smp);
		
		List lc = notifrepo.notifmembersm();
		for (int i=0;i<lc.size();i++)
        {
			Object[] cr=(Object[])lc.get(i);
			Notif notif = new Notif();
            
			notif.setNotif_isi("Pengumuman baru telah diUpload");
			notif.setNotif_flag("0");
			notif.setNotif_tanggal(java.time.LocalDate.now()+"");
			notif.setUser_id(Long.valueOf(cr[9]+""));
			notifservice.save(notif);
		}

		return "redirect:/admin/daftar_pengumuman_sm.html";
	}

	@RequestMapping("/admin/daftar_pengumuman_sm.html")
	public String viewDaftarSMP(Model model) {
		List listsmp = service.listAll();
		model.addAttribute("listsmp", listsmp);
		
		return "/admin/daftar_pengumuman_sm";
	}

    @RequestMapping("/admin/pengumuman_sm.html")
	public String showNewSMPPage(Model model) {
		SMP smp = new SMP();
		model.addAttribute("smp", smp);
		
		return "/admin/pengumuman_sm";
    }
    
	@RequestMapping("/admin/edit_smp.html/{smp_id}")
	public ModelAndView showEditSMPPage(@PathVariable(name = "smp_id") int smp_id) {
		ModelAndView mav = new ModelAndView("/admin/edit_smp.html");
		SMP smp = service.get(smp_id);
		mav.addObject("smp", smp);
		return mav;
	}
}