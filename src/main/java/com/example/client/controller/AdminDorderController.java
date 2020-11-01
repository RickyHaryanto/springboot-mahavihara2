package com.example.client.controller;
import java.util.Hashtable;
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
import com.example.client.model.Dorder;
import com.example.client.service.AdminDorderService;
import com.example.client.repository.AdminDorderRepository;

@Controller
public class AdminDorderController {
    @Autowired
	private AdminDorderService service; 
	@Autowired
	private AdminDorderRepository repo; 
	

	@RequestMapping("/admin/list_dorder.html/{horder_id}")
	public String viewDaftarDorder(Model model, @PathVariable(name = "horder_id") int horder_id) {
		List listdorder = service.listAll(horder_id+"");
		
		model.addAttribute("listdorder", listdorder);
		
		return "/admin/list_dorder";
	}



	@RequestMapping("/admin/edit_dorder.html/{dorder_id}")
	public ModelAndView editdorder(@PathVariable(name = "dorder_id") int dorder_id) {
		ModelAndView mav = new ModelAndView("/admin/edit_dorder.html");
		Dorder dorder = service.get(dorder_id);
		currentID = dorder_id;
		mav.addObject("dorder", dorder);
		
		return mav;
	}



	int currentID = 0;
	Dorder dorder = new Dorder();
	@RequestMapping(value = "/adminsave_dorder/{dorder_id}", method = RequestMethod.POST) 
	public String savedorder(@PathVariable(name = "dorder_id") Long dorder_id,
	RedirectAttributes redirectAttributes, 
	@RequestParam("dorder_jumlah") Long dorder_jumlah,
    ModelMap modelMap
    ){

        modelMap.addAttribute("dorder_jumlah", dorder_jumlah);


		dorder=service.get(currentID);
		
		//getHarga
		Integer harga = repo.findharga(dorder_id);
		//getjumlah
		Integer jumlah = dorder_jumlah.intValue();
		//subtotal
		Integer newsubtotal = harga*jumlah;

		dorder.setDorder_jumlah(dorder_jumlah);
		dorder.setDorder_subtotal(newsubtotal);
        
        
		service.save2(dorder,currentID);
		newsubtotal=0;
        return "redirect:/admin/list_horder.html";
    }


}