package com.test01.domain.article;

import com.test01.domain.user.SiteUser;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 200)
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDate createDate;
    private LocalDate modifyDate;
    @ManyToOne
    private SiteUser author;
    @ManyToMany
    Set<SiteUser> voter;
}
