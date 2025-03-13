package com.deliveryfood.pet.Service;

import com.deliveryfood.pet.models.MyUsers;
import com.deliveryfood.pet.models.PaymentCard;
import com.deliveryfood.pet.repo.PaymentCardRepository;
import com.deliveryfood.pet.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentCardRepository paymentCardRepository;

    @Autowired
    private StringEncryptor encryptor;

    @Autowired
    private UserRepository userRepository;

    public PaymentCard addCard(Long userId, PaymentCard card) {
        MyUsers user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        card.setUser(user);
        return paymentCardRepository.save(card);
    }

    public List<PaymentCard> getCardsByUser(Long userId) {
        return paymentCardRepository.findByUserId(userId);
    }

    @Transactional
    public void deleteCard(Long cardId, Long userId) {
        paymentCardRepository.deleteByIdAndUserId(cardId, userId);
    }

}
