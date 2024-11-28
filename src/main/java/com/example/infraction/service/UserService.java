package com.example.infraction.service;

import com.example.infraction.Repository.UserRepository;
//import com.example.infraction.TypeDeRole;
import com.example.infraction.TypeDeRole;
import com.example.infraction.model.Radar;
//import com.example.infraction.model.Role;
import com.example.infraction.model.Role;
import com.example.infraction.model.User;
import com.example.infraction.model.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder PasswordEncoder;

    private validationService validationService;



    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder PasswordEncoder, validationService validationService) {
        this.userRepository = userRepository;
        this.PasswordEncoder = PasswordEncoder;
        this.validationService = validationService;
    }

    public void inscription(User user) {
        if (!user.getEmail().contains("@") || !user.getEmail().contains(".")) {
            throw new RuntimeException("Votre email est invalide");
        }
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new RuntimeException("Votre email est déjà dans la base de données");
        }
        String encodedPassword = PasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        Role roleUser = new Role();
        roleUser.setLibelle(TypeDeRole.UTILISATEUR);
        user.setRole(roleUser);

        userRepository.save(user);
        this.validationService.enregistrerU(user);
    }

    public void activation(Map<String, String> activation) {

        Validation validation= this.validationService.lirefonctionDuCode(activation.get("code"));
        if(Instant.now().isAfter((validation.getExpire()))){
            throw new RuntimeException("votre code a expire");
        }
        User userActive=  this.userRepository.findById(validation.getUser().getId()).orElseThrow(() -> new RuntimeException("utilisateur inconnu"));
        userActive.setActive(true);
        this.userRepository.save(userActive);
        this.userRepository.save(userActive);}



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        assert this.userRepository != null;
        return (UserDetails) this.userRepository.findByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException("Aucun user ne correspand a cet email"));
    }


  /*  public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }*/

}
