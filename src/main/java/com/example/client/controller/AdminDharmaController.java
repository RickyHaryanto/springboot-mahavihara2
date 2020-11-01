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
import com.example.client.repository.AdminNotifRepository;
import com.example.client.service.AdminNotifService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.client.model.Notif;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.example.client.model.Dharma;
import com.example.client.service.AdminDharmaService;

@Controller
public class AdminDharmaController {
	int currentID = 0;
	
    @Autowired
	private AdminDharmaService service; 
	@Autowired
	private AdminNotifService notifservice;
	@Autowired
	private AdminNotifRepository notifrepo;

	public Dharma dharma = new Dharma();

	
	@RequestMapping(value = "/adminsave_dharma", method = RequestMethod.POST)
	public String saveDharma(RedirectAttributes redirectAttributes, 
	@RequestParam("dharma_media") MultipartFile dharma_media, ModelMap modelMap, 
	@RequestParam("dharma_isi") String dharma_isi, 
	@RequestParam("dharma_judul") String dharma_judul, 
	@RequestParam("dharma_tanggal") String dharma_tanggal) {	
	
		try {
			// Get the file and save it somewhere
			byte[] bytes = dharma_media.getBytes();
			Path currentRelativePath = Paths.get("");
			String s2 = currentRelativePath.toAbsolutePath().toString()+"\\src\\main\\resources\\static\\img\\fileDharma\\";
			Path path = Paths.get(s2 + dharma_media.getOriginalFilename());
			Files.write(path, bytes);

			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + dharma_media.getOriginalFilename() + "'");

		} catch (IOException e) {
			e.printStackTrace();
		}

		String pathfoto = "../img/fileDharma/" + dharma_media.getOriginalFilename();
		
		dharma.setDharma_isi(dharma_isi);
		dharma.setDharma_judul(dharma_judul);
		dharma.setDharma_tanggal(dharma_tanggal);
		dharma.setDharma_media(pathfoto);
		
		service.save(dharma,currentID);




		//Notif
		List lc = notifrepo.notifdharma();
		for (int i=0;i<lc.size();i++)
        {
			Object[] cr=(Object[])lc.get(i);
			Notif notif = new Notif();
            
			notif.setNotif_isi("Rekap Dharma baru telah diUpload");
			notif.setNotif_flag("0");
			notif.setNotif_tanggal(java.time.LocalDate.now()+"");
			notif.setUser_id(Long.valueOf(cr[0]+""));
			notifservice.save(notif);
		}
		return "redirect:/admin/daftar_dharma.html";
	}

	@RequestMapping("/admin/daftar_dharma.html")
	public String viewDaftarDharma(Model model) {
		List listdharma = service.listAll();
		model.addAttribute("listdharma", listdharma);
		
		return "/admin/daftar_dharma";
	}

    
	@RequestMapping("/admin/edit_dharma.html/{dharma_id}")
	public ModelAndView showEditDharmaPage(@PathVariable(name = "dharma_id") int dharma_id) {
		ModelAndView mav = new ModelAndView("/admin/edit_dharma.html");
		Dharma dharma = service.get(dharma_id);
		currentID = dharma_id;
		mav.addObject("dharma", dharma);
		return mav;
    }   
    
    @RequestMapping("/admin/upload_dharma.html")
	public String showNewDharmaPage(Model model) {
		Dharma dharma = new Dharma();
		model.addAttribute("dharma", dharma);
		currentID=0;
		return "/admin/upload_dharma";
    }
    
}