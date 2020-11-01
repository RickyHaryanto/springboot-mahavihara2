package com.example.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.example.client.model.Horder;
import com.example.client.model.Laporan;
import com.example.client.model.Notif;
import com.example.client.repository.AdminNotifRepository;
import com.example.client.repository.AdminHorderRepository;
import com.example.client.service.AdminHorderService;
import com.example.client.service.AdminNotifService;

@Controller
public class AdminHorderController {
    @Autowired
	private AdminHorderService service; 
	@Autowired
	private AdminHorderRepository horderrepo; 
	
	@Autowired
	private AdminNotifService servicenotif; 
	@Autowired
	private AdminNotifRepository repo; 
	
	@RequestMapping(value = "/adminsave_horder", method = RequestMethod.POST)
	public String saveHorder(@ModelAttribute("horder") Horder horder) {
		service.save(horder);
		
		return "redirect:/admin/list_horder.html";
	}

	@RequestMapping("/admin/list_horder.html")
	public String viewDaftarHorder(Model model) {
		List listhorder = service.listAll();
		model.addAttribute("listhorder", listhorder);
		
		return "/admin/list_horder";
	}

    
	@RequestMapping("/admin/edit_horder.html/{horder_id}")
	public ModelAndView showEditHorderPage(@PathVariable(name = "horder_id") int horder_id) {
		ModelAndView mav = new ModelAndView("/admin/edit_horder.html");
		Horder horder = service.get(horder_id);
		mav.addObject("horder", horder);
		return mav;
	}
	

	@RequestMapping("/admin/market_notif.html/{user_email}")
	public String sendnotif(Model model, @PathVariable(name = "user_email") String user_email) {
		Notif notif = new Notif();
		
		Integer iduser = repo.selectusernotif(user_email);


		model.addAttribute("notif", notif);
		model.addAttribute("iduser", iduser);
		System.out.println(iduser);

		return "/admin/market_notif";
	}



	@RequestMapping(value = "/adminsave_notif", method = RequestMethod.POST)
	public String savenotif(RedirectAttributes redirectAttributes, 
	ModelMap modelMap, 
	@RequestParam("user_id") Long user_id, 
	@RequestParam("notif_isi") String notif_isi
	) {
		Notif notif = new Notif();

		notif.setNotif_isi(notif_isi);
		notif.setUser_id(user_id);
		notif.setNotif_flag("0");
		notif.setNotif_tanggal(java.time.LocalDate.now()+"");
		
		servicenotif.save(notif);


		return "redirect:/admin/list_horder.html";
	}




	//LAPORAN
	@RequestMapping("/admin/input_laporan.html")
	public String showlaporan(Model model) {
		Laporan laporan = new Laporan();
		model.addAttribute("laporan", laporan);
		return "/admin/input_laporan";
	}
	
	
	@RequestMapping(value = "/adminsave_laporan", method = RequestMethod.POST)
	public String saveBerita(RedirectAttributes redirectAttributes, 
	@RequestParam("startdate") String startdate, Model model, 
	@RequestParam("enddate") String enddate
	) {	
		
		System.out.println(startdate);
		System.out.println(enddate);
		
		List listlaporan = horderrepo.laporan(startdate,enddate);
		model.addAttribute("listlaporan", listlaporan);

		return "/admin/listlaporan.html";
	}

}