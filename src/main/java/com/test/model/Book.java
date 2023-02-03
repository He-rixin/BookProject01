package com.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {
    private Integer bookId;

    private String bookName;

    private String bookNamePinyin;

    private Long   bookCategoryId;

    private String bookAuthor;

    private Float  bookPrice;

    private Long   bookImage;

    private String publishing;

    private String  bookDesc;
    private Integer  bookState;

    private Date    deployDatetime;

    private Integer  salesVolume;


}