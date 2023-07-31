package com.xcode.currencyservice.repository;

import com.xcode.currencyservice.model.CurrencyHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<CurrencyHistory, Long> {}