package com.repo;

import com.domain.StockPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockPortfolioRepository extends JpaRepository<StockPortfolio, Integer> {
}
