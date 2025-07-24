package com.springboot_mission._250723_springboot_mission_bookmgmt.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Book {
    private Long id;
    private String title;
    private Long authorId;
    private LocalDate publishedDate;
}
