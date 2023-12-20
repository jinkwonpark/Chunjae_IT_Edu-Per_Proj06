package com.pro06.controller;

import com.pro06.dto.NoticeDTO;
import com.pro06.dto.PageRequestDTO;
import com.pro06.dto.PageResponseDTO;
import com.pro06.service.NoticeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/notice")
@Log4j2
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    // 공지사항 목록
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping({"notice", "/noticeList"})
    public String noticeList(PageRequestDTO pageRequestDTO, Model model){
        PageResponseDTO<NoticeDTO> responseDTO = noticeService.noticeList(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);
        return "notice/noticeList";
    }

    // 공지사항 상세보기
    @GetMapping("/noticeDetail")
    public String noticeDetail(Long nno, Model model){
        NoticeDTO noticeDTO = noticeService.noticeDetail(nno);
        model.addAttribute("dto", noticeDTO);
        return "notice/noticeDetail";
    }

    // 공지사항 글쓰기
    @GetMapping("/noticeWrite")
    public String noticeWriteForm(){
        return "notice/noticeWrite";
    }

    @PostMapping("/noticeWrite")
    public String noticeWrite(@Valid NoticeDTO noticeDTO){
        noticeService.noticeWrite(noticeDTO);
        return "redirect:/notice/noticeList";
    }

    // 공지사항 수정하기
    @GetMapping("/noticeEdit")
    public String noticeEditForm(Long nno, Model model){
        NoticeDTO noticeDTO = noticeService.noticeDetail(nno);
        model.addAttribute("dto", noticeDTO);
        return "notice/noticeEdit";
    }

    @PostMapping("/noticeEdit")
    public String noticeEdit(@Valid NoticeDTO noticeDTO){
        noticeService.noticeEdit(noticeDTO);
        return "redirect:/notice/noticeList";
    }

    // 공지사항 삭제하기
    @PostMapping("/noticeDelete")
    public String noticeDelete(Long nno){
        noticeService.noticeDelete(nno);
        return "redirect:/notice/noticeList";
    }
}
