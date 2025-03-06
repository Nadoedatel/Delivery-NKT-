package com.deliveryfood.pet.Service;

import com.deliveryfood.pet.Config.MyUserDetails;
import com.deliveryfood.pet.models.MyUsers;
import com.deliveryfood.pet.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<MyUsers> users = repository.findByUsername(username);

        return users.map(MyUserDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException(username + "Not Found"));
    };

}
