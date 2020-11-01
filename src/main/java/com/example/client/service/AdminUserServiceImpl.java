package com.example.client.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.client.model.Role;
import com.example.client.model.User;
import com.example.client.repository.AdminUserRepository;
import com.example.client.dto.UserRegistrationDto;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User save(UserRegistrationDto registration){
        User user = new User();
        user.setNama(registration.getNama());
        user.setEmail(registration.getEmail());
        user.setAlamat(registration.getAlamat());
        user.setBod(registration.getBod());
        user.setQiudao(registration.getQiudao());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setStatus("Aktif");
        user.setStatusBerita("Aktif");
        user.setKota(registration.getKota());
        user.setRoles(Arrays.asList(new Role(registration.getJabatan())));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        if (user.getStatus().equalsIgnoreCase("Aktif")){
            return new org.springframework.security.core.userdetails.User(user.getEmail(),
            user.getPassword(),
            mapRolesToAuthorities(user.getRoles()));
        }else{
            throw new UsernameNotFoundException("User Is Not Active.");
        }
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    public List showAll() {
		return userRepository.findAllMember();
    }
    
    public User get(long user_id) {
		return userRepository.findById(user_id).get();
	}
    
    public void save2(User user, long user_id) {
        if (user_id != 0){
            //user=set(user_id);
			userRepository.save(user);
		}else{
			userRepository.save(user);
		}	
	}
}
