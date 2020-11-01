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
import com.example.client.service.EventService;

@Controller
public class EventController {
    @Autowired
    private EventService service; 
    
    @RequestMapping(value = "/save_event", method = RequestMethod.POST)
	public String saveEvent(@ModelAttribute("event") Event event) {
		service.save(event);
		
		return "redirect:/list_event.html";
	}

	@RequestMapping("/list_event.html")
	public String viewDaftarEvent(Model model) {
		List<Event> listevent = service.listAll();
		model.addAttribute("listevent", listevent);
		
		return "list_event";
	}

	@RequestMapping("/list_event_user.html")
	public String viewDaftarEventUser(Model model) {
		List<Event> listevent = service.listAll();
		model.addAttribute("listevent", listevent);
		
		return "list_event_user";
	}

    @RequestMapping("/add_event.html")
	public String showNewEventPage(Model model) {
		Event event = new Event();
		model.addAttribute("event", event);
		
		return "add_event";
    }
    
	@RequestMapping("/edit_event.html/{event_id}")
	public ModelAndView showEditEventPage(@PathVariable(name = "event_id") int event_id) {
		ModelAndView mav = new ModelAndView("edit_event.html");
		Event event = service.get(event_id);
		mav.addObject("event", event);
		return mav;
	}



@RequestMapping("/googlecalendar.html")
	public String viewgc(Model model) {
		
		
		return "googlecalendar";
	}
}