package com.jy.gongdae.repository;

import com.jy.gongdae.domain.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepo extends JpaRepository<SiteUser, Long>{
    Optional<SiteUser> findByUsername(String username);
}