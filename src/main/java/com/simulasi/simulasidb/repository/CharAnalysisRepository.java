package com.simulasi.simulasidb.repository;

import com.simulasi.simulasidb.entity.CharAnalysis;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CharAnalysisRepository extends JpaRepository<CharAnalysis, Long> {
    List<CharAnalysis> findByKalimatContainingIgnoreCase(String kata);

    @Query("select c from CharAnalysis c where c.totalHuruf >= :min")
    List<CharAnalysis> findWithMinimumTotalHuruf(@Param("min") int min);

    @Query(value = "select * from mst_char_analysis order by created_at desc limit 5", nativeQuery = true)
    List<CharAnalysis> findLatestFive();
}
