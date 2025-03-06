package com.deliveryfood.pet.Service;

import com.deliveryfood.pet.models.MyUsers;
import com.deliveryfood.pet.models.User;
import com.deliveryfood.pet.repo.UserRepository;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;


@Service
@AllArgsConstructor
public class UserService {

    private List<User> appUser;
    private UserRepository repository;
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void loadUserInDB(){
        Faker faker = new Faker();
        appUser = IntStream.rangeClosed(1, 20)
                .mapToObj(i -> User.builder()
                    .id(i)
                    .name(faker.address().firstName())
                    .lastname(faker.address().lastName())
                    .phone(faker.phoneNumber().phoneNumber())
                    .address(faker.address().fullAddress())
                    .card(faker.business().creditCardNumber())
                    .build())
                .toList();
    }

    public List<User> allUser() {
        return appUser;
    }

    public User userByID(int id) {
        return appUser.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addUser(MyUsers users) {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        repository.save(users);
    }


}
