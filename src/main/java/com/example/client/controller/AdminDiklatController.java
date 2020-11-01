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


import com.example.client.model.Diklat;
import com.example.client.service.AdminDiklatService;

@Controller
public class AdminDiklatController {
	int currentID = 0;
	
	@Autowired
	private AdminDiklatService service; 
	
	@RequestMapping("/admin/daftar_diklat.html")
	public String viewDaftarDiklat(Model model) {
		List listDiklat = service.listAll();
		model.addAttribute("listDiklat", listDiklat);
		
		return "/admin/daftar_diklat";
	}

	@RequestMapping("/admin/edit_diklat.html/{diklat_id}")
	public ModelAndView showEditDiklatPage(@PathVariable(name = "diklat_id") int diklat_id) {
		ModelAndView mav = new ModelAndView("/admin/edit_diklat.html");
		Diklat diklat = service.get(diklat_id);
		currentID = diklat_id;
		mav.addObject("diklat", diklat);
		
		return mav;
	}

	@RequestMapping("/admin/upload_diklat.html")
	public String showNewDiklatPage(Model model) {
		Diklat diklat = new Diklat();
		model.addAttribute("diklat", diklat);
		currentID=0;
		return "/admin/upload_diklat";
	}
	
	
	private Diklat diklat = new Diklat();

	@RequestMapping(value = "/adminsave_diklat", method = RequestMethod.POST)
	public String saveDiklat(RedirectAttributes redirectAttributes, 
	@RequestParam("diklat_foto") MultipartFile diklat_foto, ModelMap modelMap, @RequestParam("diklat_nama") String diklat_nama, 
	@RequestParam("diklat_start") String diklat_start, @RequestParam("diklat_end") String diklat_end,
	@RequestParam("diklat_status") String diklat_status, @RequestParam("diklat_keterangan") String diklat_keterangan, 
	@RequestParam("diklat_bataswaktu") String diklat_bataswaktu) {
		
		
		modelMap.addAttribute("diklat_foto", diklat_foto);
		modelMap.addAttribute("diklat_nama", diklat_nama);
		modelMap.addAttribute("diklat_start", diklat_start);
		modelMap.addAttribute("diklat_end", diklat_end);
		modelMap.addAttribute("diklat_status", diklat_status);
		modelMap.addAttribute("diklat_keterangan", diklat_keterangan);
		modelMap.addAttribute("diklat_bataswaktu", diklat_bataswaktu);
		
		try {
			// Get the file and save it somewhere
			byte[] bytes = diklat_foto.getBytes();
			Path currentRelativePath = Paths.get("");
			String s2 = currentRelativePath.toAbsolutePath().toString()+"\\src\\main\\resources\\static\\img\\imgDiklat\\";
			Path path = Paths.get(s2 + diklat_foto.getOriginalFilename());
			Files.write(path, bytes);

			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + diklat_foto.getOriginalFilename() + "'");

		} catch (IOException e) {
			e.printStackTrace();
		}

		String pathfoto = "img/imgDiklat/" + diklat_foto.getOriginalFilename();

		diklat.setDiklat_nama(diklat_nama);
		diklat.setDiklat_start(diklat_start);
		diklat.setDiklat_end(diklat_end);
		diklat.setDiklat_foto(pathfoto);
		diklat.setDiklat_status(diklat_status);
		diklat.setDiklat_keterangan(diklat_keterangan);
		diklat.setDiklat_bataswaktu(diklat_bataswaktu);
		
		service.save(diklat,currentID);
		return "redirect:/admin/daftar_diklat.html";
		}

}
