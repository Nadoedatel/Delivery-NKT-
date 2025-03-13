package com.deliveryfood.pet.repo;

import com.deliveryfood.pet.models.PaymentCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentCardRepository extends JpaRepository<PaymentCard, Long> {
    List<PaymentCard> findByUserId(Long userId);
    void deleteByIdAndUserId(Long cardId, Long userId);
}
