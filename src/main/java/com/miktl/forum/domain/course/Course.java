package com.miktl.forum.domain.course;

import jakarta.persistence.*;
import lombok.Getter;

@Entity(name = "Course")
@Table(name = "courses")
@Getter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;
}
