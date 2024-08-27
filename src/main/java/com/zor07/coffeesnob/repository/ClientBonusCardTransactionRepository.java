package com.zor07.coffeesnob.repository;

import com.zor07.coffeesnob.entity.ClientBonusCardTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientBonusCardTransactionRepository extends JpaRepository<ClientBonusCardTransaction, Long> {
}