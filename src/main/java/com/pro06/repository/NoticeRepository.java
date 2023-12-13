package com.pro06.repository;

import com.pro06.domain.Notice;
import com.pro06.repository.search.NoticeSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NoticeRepository extends JpaRepository<Notice, Long>, NoticeSearch {

    @Query(value = "select now()", nativeQuery = true)
    String getTime();
}
