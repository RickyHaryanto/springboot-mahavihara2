package com.example.client.controller;
import java.util.List;
import javax.validation.Valid;
import com.example.client.model.Berita;
import com.example.client.service.BeritaService;
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
import com.example.client.service.UserService;
import com.example.client.service.UserServiceImpl;
import com.example.client.dto.UserRegistrationDto;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.security.core.context.SecurityContextHolder;


import com.example.client.model.Notif;
import com.example.client.repository.NotifRepository;
import com.example.client.service.NotifService;

@Controller
// @RequestMapping("/registration")
public class UserRegistrationController {

    
    @Autowired
    private NotifService notifservice;
    @Autowired
    private NotifRepository notifrepo;


    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceImpl service2;

    @Autowired
	private BeritaService beritaservice; 
    
    private UserRegistrationDto userDto = new UserRegistrationDto();

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @RequestMapping(value = "/registration") 
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @RequestMapping(value = "/registrationUpload", method = RequestMethod.POST) 
    public String registerUserAccount(RedirectAttributes redirectAttributes, 
    @RequestParam("nama") String nama,@RequestParam("bod") String bod, @RequestParam("alamat") String alamat, @RequestParam("email") String email,
    @RequestParam("qiudao") String qiudao,  @RequestParam("kota") String kota,
    @RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword,
    @RequestParam("jabatan") String jabatan,
    ModelMap modelMap
    ){

        modelMap.addAttribute("nama", nama);
        modelMap.addAttribute("bod", bod);
        modelMap.addAttribute("alamat", alamat);
        modelMap.addAttribute("email", email);
        modelMap.addAttribute("qiudao", qiudao);
        modelMap.addAttribute("kota", kota);
        modelMap.addAttribute("password", password);
        modelMap.addAttribute("confirmPassword", confirmPassword);
        

        User existing = userService.findByEmail(email);
        if (existing != null){
            // result.rejectValue("email", null, "There is already an account registered with that email");
            return "registration";
        }

        userDto.setNama(nama);
        userDto.setAlamat(alamat);
        userDto.setBod(bod);
        userDto.setEmail(email);
        userDto.setQiudao(qiudao);
        userDto.setKota(kota);
        userDto.setPassword(password);
        userDto.setConfirmPassword(confirmPassword);
        userDto.setJabatan(jabatan);
       
        userService.save(userDto);

        //Notif
		List lc = notifrepo.notifchat();
		for (int i=0;i<lc.size();i++)
        {
			Object[] cr=(Object[])lc.get(i);
			Notif notif = new Notif();
            
			notif.setNotif_isi(email+"telah mendaftar");
			notif.setNotif_flag("0");
			notif.setNotif_tanggal(java.time.LocalDate.now()+"");
			notif.setUser_id(Long.valueOf(cr[0]+""));
			notifservice.save(notif);
		}

        return "redirect:/registration?success";
    }

    @RequestMapping("/berita.html")
    public String cekberita(Model model,HttpSession session){
        try{
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User usr=service2.findByEmail(user.getUsername());
            String stat = usr.getStatusBerita();
            if (stat.equals( "Tidak Aktif")){
                return "pendaftaranberita";
            }else{
                List listberita = beritaservice.listAll();
                model.addAttribute("listberita", listberita);
                return "listberita";
            }
        }catch(Exception e){
        }
        return "home";
    }

    @RequestMapping("/pendaftaranberita.html")
	public String showNewPendaftaranPage(Model model,HttpSession session) {
	
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usr=userService.findByEmail(user.getUsername());
		model.addAttribute("test2",usr.getUser_id());

        model.addAttribute("user", user);
		return "pendaftaranberita";
    }
    

  
    public User user = new User();
    @RequestMapping(value = "/registrationUpload2", method = RequestMethod.POST) 
    public String registerUserAccount2(RedirectAttributes redirectAttributes, 
    ModelMap modelMap
    ){

        org.springframework.security.core.userdetails.User userID = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User usr=userService.findByEmail(userID.getUsername());
        
        
        user=service2.get(usr.getUser_id());
        user.setStatusBerita("Aktif");
        
        
        service2.save2(user,usr.getUser_id());
        return "redirect:/home.html?success";
    }
}
