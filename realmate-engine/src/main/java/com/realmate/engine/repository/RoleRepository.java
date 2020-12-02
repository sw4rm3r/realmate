package com.realmate.engine.repository;

import java.util.Optional;

import com.realmate.engine.models.ERole;
import com.realmate.engine.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
