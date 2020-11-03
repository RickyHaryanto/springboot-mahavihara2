package com.example.client.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.example.client.model.User;
import com.example.client.service.AdminUserService;
import com.example.client.service.AdminUserServiceImpl;
import com.example.client.dto.UserRegistrationDto;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller

public class AdminUserRegistrationController {

  
    @Autowired
    private AdminUserService userService;

    @Autowired
    private AdminUserServiceImpl userServiceimpl;

    private UserRegistrationDto userDto = new UserRegistrationDto();

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @RequestMapping(value = "/adminregistration") 
    public String showRegistrationForm(Model model) {
        return "/adminregistration";
    }

    
    @RequestMapping(value = "/adminregistrationUpload", method = RequestMethod.POST) 
    public String registerUserAccount(RedirectAttributes redirectAttributes, 
    @RequestParam("nama") String nama,@RequestParam("bod") String bod, @RequestParam("alamat") String alamat, @RequestParam("email") String email,
    @RequestParam("qiudao") String qiudao, @RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword,
    @RequestParam("jabatan") String jabatan, @RequestParam("kota") String kota, ModelMap modelMap
    ){

        modelMap.addAttribute("nama", nama);
        modelMap.addAttribute("bod", bod);
        modelMap.addAttribute("alamat", alamat);
        modelMap.addAttribute("email", email);
        modelMap.addAttribute("qiudao", qiudao);
        modelMap.addAttribute("password", password);
        modelMap.addAttribute("confirmPassword", confirmPassword);
        modelMap.addAttribute("jabatan", jabatan);
        modelMap.addAttribute("kota", kota);
       

        User existing = userService.findByEmail(email);
        if (existing != null){
            // result.rejectValue("email", null, "There is already an account registered with that email");
            return "/admin/registration";
        }

        userDto.setNama(nama);
        userDto.setAlamat(alamat);
        userDto.setBod(bod);
        userDto.setEmail(email);
        userDto.setQiudao(qiudao);
        userDto.setPassword(password);
        userDto.setConfirmPassword(confirmPassword);
        userDto.setJabatan(jabatan);
        userDto.setKota(kota);
        userService.save(userDto);
        return "redirect:/admin/registration?success";
    }


    @RequestMapping("/admin/umat.html")
    public String viewUmatPage(Model model) {
        List listumat = userServiceimpl.showAll();
        model.addAttribute("listumat", listumat);
        
        return "/admin/umat";
    }

    @RequestMapping("/admin/edit_umat.html/{user_id}")
	public ModelAndView showEditUmatPage(@PathVariable(name = "user_id") int user_id) {
		ModelAndView mav = new ModelAndView("/admin/edit_umat.html");
        User user = userServiceimpl.get(user_id);
        currentID = user_id;
		mav.addObject("user", user);
		return mav;
    }
    
    int currentID = 0;
    public User user = new User();
    @RequestMapping(value = "/adminregistrationUpload2", method = RequestMethod.POST) 
    public String registerUserAccount2(RedirectAttributes redirectAttributes, 
    @RequestParam("status") String status,
    ModelMap modelMap
    ){

        modelMap.addAttribute("status", status);
        
        user=userServiceimpl.get(currentID);
        user.setStatus(status);
        
        
        userServiceimpl.save2(user,currentID);
        return "redirect:/admin/umat.html";
    }

}
