package com.repo;

import com.domain.Child;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildRepo extends JpaRepository<Child, Integer> {
}
