package com.grouper.grouper_repository;

import com.grouper.grouper_model.GrouperUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GrouperUserRepository extends JpaRepository<GrouperUser, Integer> {
    Optional<GrouperUser> findByUsername(String username);
}
