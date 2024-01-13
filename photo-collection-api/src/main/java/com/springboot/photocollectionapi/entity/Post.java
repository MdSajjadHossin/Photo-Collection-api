package com.springboot.photocollectionapi.entity;

import jakarta.persistence.*;
import jdk.jfr.Category;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Entity
@Data
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    private String title;

    private String imageDescription;

    public Date addeddate;

    // relationship mapping
    @ManyToOne
    public PhotoCategory category;

    @ManyToOne
    private User user;

}
