package com.example.client.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.ModelMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import com.example.client.model.Barang;
import com.example.client.service.AdminBarangService;

@Controller
public class AdminBarangController {
	int currentID = 0;
	@Autowired
	private AdminBarangService service; 
	

	
	private Barang barang = new Barang();

	@RequestMapping(value = "/adminsave_barang", method = RequestMethod.POST)
	public String saveBarang(RedirectAttributes redirectAttributes, 
	@RequestParam("barang_foto") MultipartFile barang_foto, ModelMap modelMap, 
	@RequestParam("barang_nama") String barang_nama, 
	@RequestParam("barang_keterangan") String barang_keterangan, 
	@RequestParam("barang_harga") Integer barang_harga) {

		modelMap.addAttribute("barang_foto", barang_foto);
		modelMap.addAttribute("barang_nama", barang_nama);
		modelMap.addAttribute("barang_keterangan", barang_keterangan);
		modelMap.addAttribute("barang_harga", barang_harga);
		
		try {
			// Get the file and save it somewhere
			byte[] bytes = barang_foto.getBytes();
			Path currentRelativePath = Paths.get("");
			String s2 = currentRelativePath.toAbsolutePath().toString()+"\\src\\main\\resources\\static\\img\\imgBarang\\";
			Path path = Paths.get(s2 + barang_foto.getOriginalFilename());
			Files.write(path, bytes);

			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + barang_foto.getOriginalFilename() + "'");

		} catch (IOException e) {
			e.printStackTrace();
		}

		String pathfoto = "img/imgBarang/" + barang_foto.getOriginalFilename();
		
		barang.setBarang_nama(barang_nama);
		barang.setBarang_keterangan(barang_keterangan);
		barang.setBarang_harga(barang_harga);
		barang.setBarang_foto(pathfoto);
		
		service.save(barang,currentID);
		return "redirect:/admin/listbarang.html";
	}

	@RequestMapping("/admin/listbarang.html")
	public String viewDaftarBarang(Model model) {
		List listbarang = service.listAll();
		model.addAttribute("listbarang", listbarang);
		
		return "/admin/listbarang";
	}

    @RequestMapping("/admin/add_product_market.html")
	public String showNewBarangPage(Model model) {
		Barang barang = new Barang();
		model.addAttribute("barang", barang);
		currentID=0;
		return "/admin/add_product_market";
    }
	
	

	@RequestMapping("/admin/edit_barang.html/{barang_id}")
	public ModelAndView showEditBarangPage(@PathVariable(name = "barang_id") int barang_id) {
		ModelAndView mav = new ModelAndView("/admin/edit_barang.html");
		Barang barang = service.get(barang_id);
		currentID = barang_id;
		mav.addObject("barang", barang);
		return mav;
	}

	

}