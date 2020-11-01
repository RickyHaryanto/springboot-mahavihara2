package com.example.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.client.repository.AdminNotifRepository;
import javax.servlet.http.HttpSession;
import com.example.client.model.User;
import com.example.client.service.AdminUserService;

@Controller
public class AdminMainController {
    @Autowired
	private AdminUserService service2;
    @Autowired
    private AdminNotifRepository reponotif;
    

    @GetMapping("/admin/index.html")
    public String index(Model model) {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usr=service2.findByEmail(user.getUsername());
		model.addAttribute("haloindex",usr.getNama());
        return "/admin/index";
    }


    @GetMapping("/admin/index_market.html")
    public String indexMarket(Model model) {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usr=service2.findByEmail(user.getUsername());
		model.addAttribute("haloindexmarket",usr.getNama());
        return "/admin/index_market";
    }

    @GetMapping("/admin/index_sm.html")
    public String indexSm(Model model) {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usr=service2.findByEmail(user.getUsername());
		model.addAttribute("haloindexsm",usr.getNama());
        return "/admin/index_sm";
    }

    @GetMapping("/admin/index_event.html")
    public String indexEvent(Model model) {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usr=service2.findByEmail(user.getUsername());
		model.addAttribute("haloindexevent",usr.getNama());
        return "/admin/index_event";
    }

    /*@GetMapping("/admin/login")
    public String login(Model model) {
        return "/admin/login";
    }*/

    /*@GetMapping("/logout.html")
    public String logout(Model model,HttpSession session) {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "login";
    }*/


    @GetMapping("/serviceadminumum")
    @ResponseBody
    public java.util.List serviceadminumum() {
        java.util.List lc = reponotif.selectindex();
        return lc;
    }

    @GetMapping("/serviceadminsm")
    @ResponseBody
    public java.util.List serviceadminsm() {
        java.util.List lc = reponotif.selectindexsm();
        return lc;
    }


    @GetMapping("/serviceadminevent")
    @ResponseBody
    public java.util.List serviceadminevent() {
        java.util.List lc = reponotif.selectindexevent();
        return lc;
    }


    @GetMapping("/serviceadminmarket")
    @ResponseBody
    public java.util.List serviceadminumummarket() {
        java.util.List lc = reponotif.selectindexmarket();
        return lc;
    }

    @GetMapping("/admin/laporan.html")
    public String laporan(Model model) {
        return "/admin/laporan";
    }


    @GetMapping("/Test/halo.html")
    public String halo(Model model) {
        return "/Test/halo";
    }
}
