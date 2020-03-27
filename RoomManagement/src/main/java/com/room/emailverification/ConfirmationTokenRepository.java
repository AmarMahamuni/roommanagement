package com.room.emailverification;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String> {
     ConfirmationToken findByConfirmationToken(String confirmationToken);
}