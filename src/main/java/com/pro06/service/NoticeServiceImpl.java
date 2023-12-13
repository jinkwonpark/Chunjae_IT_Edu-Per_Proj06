package com.pro06.service;

import com.pro06.domain.Notice;
import com.pro06.dto.NoticeDTO;
import com.pro06.dto.PageRequestDTO;
import com.pro06.dto.PageResponseDTO;
import com.pro06.repository.NoticeRepository;
import groovy.util.logging.Log4j2;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class NoticeServiceImpl implements NoticeService{

    private final ModelMapper modelMapper;

    private final NoticeRepository noticeRepository;

    // 공지사항 목록/검색 기능
    @Override
    public PageResponseDTO<NoticeDTO> noticeList(PageRequestDTO pageRequestDTO) {
        String[] types = pageRequestDTO.getTypes();;
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("nno");
        Page<Notice> result = noticeRepository.searchAll(types, keyword, pageable);

        List<NoticeDTO> dtoList = result.getContent().stream()
                .map(notice -> modelMapper.map(notice, NoticeDTO.class)).collect(Collectors.toList());

        return PageResponseDTO.<NoticeDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }

    // 공지사항 상세보기
    @Override
    public NoticeDTO noticeDetail(Long nno) {
        Optional<Notice> result = noticeRepository.findById(nno);
        Notice notice = result.orElseThrow(); // 예외 처리
        NoticeDTO noticeDTO = modelMapper.map(notice, NoticeDTO.class);
        return noticeDTO;
    }

    // 공지사항 글쓰기
    @Override
    public Long noticeWrite(NoticeDTO noticeDTO) {
        Notice notice = modelMapper.map(noticeDTO, Notice.class);
        Long nno = noticeRepository.save(notice).getNno();
        return nno;
    }

    // 공지사항 수정하기
    @Override
    public void noticeEdit(NoticeDTO noticeDTO) {
        Optional<Notice> result = noticeRepository.findById(noticeDTO.getNno());
        Notice notice = result.orElseThrow(); // 예외 처리
        notice.change(noticeDTO.getTitle(), noticeDTO.getContent());
        noticeRepository.save(notice);
    }

    // 공지사항 삭제하기
    @Override
    public void noticeDelete(Long nno) {
        noticeRepository.deleteById(nno);
    }
}
