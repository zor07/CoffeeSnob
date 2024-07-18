package com.zor07.coffeesnob.repository;

import com.zor07.coffeesnob.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByChatId(Long chatId);

}
