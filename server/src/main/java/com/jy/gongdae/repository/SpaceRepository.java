package com.jy.gongdae.repository;

import com.jy.gongdae.domain.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Long> {

    @Query("SELECT s FROM Space s ORDER BY s.createDate ASC")
    List<Space> findSpaceAllAsc();
}
