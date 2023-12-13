package com.pro06.repository.search;

import com.pro06.domain.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeSearch {

    Page<Notice> search1(Pageable pageable);

    Page<Notice> searchAll(String[] types, String keyword, Pageable pageable);
}
