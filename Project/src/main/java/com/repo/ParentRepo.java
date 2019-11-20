package com.repo;

import com.domain.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepo extends JpaRepository<Parent, Integer> {
}
