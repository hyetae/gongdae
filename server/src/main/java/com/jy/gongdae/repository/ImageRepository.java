package com.jy.gongdae.repository;

import com.jy.gongdae.domain.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Images, Long> {

    @Query("SELECT i FROM Images i WHERE i.space.id = :id ORDER BY i.createDate ASC")
    List<Images> findImageAllAsc(Long id);
}
