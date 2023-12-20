package com.pro06.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO {

    private Long nno;

    @NotEmpty
    @Size(min = 1, max = 200)
    private String title;

    @NotEmpty
    @Size(min = 1, max = 2000)
    private String content;

    private LocalDateTime regDate;

    private LocalDateTime modDate;
}
