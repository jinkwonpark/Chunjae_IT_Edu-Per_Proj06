package com.pro06.service;

import com.pro06.dto.NoticeDTO;
import com.pro06.dto.PageRequestDTO;
import com.pro06.dto.PageResponseDTO;

import java.util.List;

public interface NoticeService {

    // 공지사항 목록/검색 기능
    PageResponseDTO<NoticeDTO> noticeList(PageRequestDTO pageRequestDTO);

    // 공지사항 상세보기
    NoticeDTO noticeDetail(Long nno);

    // 공지사항 글쓰기
    Long noticeWrite(NoticeDTO noticeDTO);

    // 공지사항 수정하기
    void noticeEdit(NoticeDTO noticeDTO);

    // 공지사항 삭제하기
    void noticeDelete(Long nno);
}