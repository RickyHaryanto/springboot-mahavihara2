package com.example.client.controller;
import java.util.List;

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
import com.example.client.repository.AdminNotifRepository;
import com.example.client.service.AdminNotifService;
import com.example.client.model.Notif;
import com.example.client.model.Berita;
import com.example.client.service.AdminBeritaService;

@Controller
public class AdminBeritaController {
	int currentID = 0;

	
	@Autowired
	private AdminBeritaService service; 
	@Autowired
private AdminNotifService notifservice;
@Autowired
private AdminNotifRepository notifrepo;

	private Berita berita = new Berita();
	@RequestMapping(value = "/adminsave_berita", method = RequestMethod.POST)
	public String saveBerita(RedirectAttributes redirectAttributes, 
	@RequestParam("berita_file") MultipartFile berita_file, ModelMap modelMap, 
	@RequestParam("berita_judul") String berita_judul, 
	@RequestParam("berita_isi") String berita_isi, 
	@RequestParam("berita_tanggal") String berita_tanggal) {	
	
		modelMap.addAttribute("berita_file", berita_file);
		modelMap.addAttribute("berita_judul", berita_judul);
		modelMap.addAttribute("berita_isi", berita_isi);
		modelMap.addAttribute("berita_tanggal", berita_tanggal);
		
		try {
			// Get the file and save it somewhere
			byte[] bytes = berita_file.getBytes();
			Path currentRelativePath = Paths.get("");
			String s2 = currentRelativePath.toAbsolutePath().toString()+"\\src\\main\\resources\\static\\img\\fileBerita\\";
			Path path = Paths.get(s2 + berita_file.getOriginalFilename());
			Files.write(path, bytes);
			
			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + berita_file.getOriginalFilename() + "'");

		} catch (IOException e) {
			e.printStackTrace();
		}

		String pathfoto = "../img/fileBerita/" + berita_file.getOriginalFilename();

		berita.setBerita_tanggal(berita_tanggal);
		berita.setBerita_judul(berita_judul);
		berita.setBerita_isi(berita_isi);
		berita.setBerita_tanggal(berita_tanggal);
		berita.setBerita_file(pathfoto);
		

		service.save(berita,currentID);
		
		List lc = notifrepo.notifberita();
		for (int i=0;i<lc.size();i++)
        {
			Object[] cr=(Object[])lc.get(i);
			Notif notif = new Notif();
            
			notif.setNotif_isi("Berita baru telah diUpload");
			notif.setNotif_flag("0");
			notif.setNotif_tanggal(java.time.LocalDate.now()+"");
			notif.setUser_id(Long.valueOf(cr[0]+""));
			notifservice.save(notif);
		}


		return "redirect:/admin/listberita.html";
	}

	@RequestMapping("/admin/listberita.html")
	public String viewDaftarBerita(Model model) {
		List listberita = service.listAll();
		model.addAttribute("listberita", listberita);
		
		return "/admin/listberita";
	}

	@RequestMapping("/admin/upload_berita.html")
	public String showNewBeritaPage(Model model) {
		Berita berita = new Berita();
		model.addAttribute("berita", berita);
		currentID=0;
		return "/admin/upload_berita";
    }
    
	@RequestMapping("/admin/edit_berita.html/{berita_id}")
	public ModelAndView showEditBeritaPage(@PathVariable(name = "berita_id") int berita_id) {
		ModelAndView mav = new ModelAndView("/admin/edit_berita.html");
		Berita berita = service.get(berita_id);
		currentID = berita_id;
		mav.addObject("berita", berita);
		return mav;
	}
}