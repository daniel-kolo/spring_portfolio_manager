package com.repo;

import com.domain.StockInstance;
import org.springframework.data.repository.CrudRepository;

public interface StockInstanceRepository extends CrudRepository<StockInstance, Integer> {
    //StockInstance.findByName(String name);
}
