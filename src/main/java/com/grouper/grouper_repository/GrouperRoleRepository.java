package com.grouper.grouper_repository;

import com.grouper.grouper_model.GrouperRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GrouperRoleRepository extends JpaRepository<GrouperRole, Integer> {
    Optional<GrouperRole> findByAuthority(String authority);
}
