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

import com.example.client.model.Event;
import com.example.client.service.AdminEventService;

@Controller
public class AdminEventController {
    @Autowired
    private AdminEventService service; 
	int currentID = 0;
	private Event event = new Event();


    @RequestMapping(value = "/adminsave_event", method = RequestMethod.POST)
	public String saveEvent(RedirectAttributes redirectAttributes, 
	@RequestParam("event_foto") MultipartFile event_foto, ModelMap modelMap, 
	@RequestParam("event_judul") String event_judul, 
	@RequestParam("event_keterangan") String event_keterangan,
	@RequestParam("event_tanggal") String event_tanggal,
	@RequestParam("event_waktu") String event_waktu) {
		
		try {
			// Get the file and save it somewhere
			byte[] bytes = event_foto.getBytes();
			Path currentRelativePath = Paths.get("");
			String s2 = currentRelativePath.toAbsolutePath().toString()+"\\src\\main\\resources\\static\\img\\imgEvent\\";
			Path path = Paths.get(s2 + event_foto.getOriginalFilename());
			Files.write(path, bytes);

			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + event_foto.getOriginalFilename() + "'");

		} catch (IOException e) {
			e.printStackTrace();
		}

		String pathfoto = "img/imgEvent/" + event_foto.getOriginalFilename();


		event.setEvent_judul(event_judul);
		event.setEvent_keterangan(event_keterangan);
		event.setEvent_tanggal(event_tanggal);
		event.setEvent_waktu(event_waktu);
		event.setEvent_foto(pathfoto);
	
		
		service.save(event,currentID);
		
		return "redirect:/admin/list_event.html";
	}

	@RequestMapping("/admin/list_event.html")
	public String viewDaftarEvent(Model model) {
		List listevent = service.listAll();
		model.addAttribute("listevent", listevent);
		
		return "/admin/list_event";
	}

    @RequestMapping("/admin/add_event.html")
	public String showNewEventPage(Model model) {
		Event event = new Event();
		model.addAttribute("event", event);
		currentID=0;
		return "/admin/add_event.html";
    }
    
	@RequestMapping("/admin/edit_event.html/{event_id}")
	public ModelAndView showEditEventPage(@PathVariable(name = "event_id") int event_id) {
		ModelAndView mav = new ModelAndView("/admin/edit_event.html");
		Event event = service.get(event_id);
		currentID = event_id;
		mav.addObject("event", event);
		return mav;
	}


	@RequestMapping("/admin/googlecalendar.html")
	public String viewgc(Model model) {
		
		
		return "/admin/googlecalendar";
	}
}