package com.repo;

import com.domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepo extends JpaRepository<Test, Integer> {
}
