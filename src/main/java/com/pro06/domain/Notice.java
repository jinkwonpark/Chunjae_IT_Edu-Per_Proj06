package com.pro06.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notice extends BaseEntity{

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long nno;

    @Column(length = 200, nullable = false)
    private String title;

    @Column(length = 2000, nullable = false)
    private String content;

    public void change(String title, String content){
        this.title = title;
        this.content = content;
    }
}
