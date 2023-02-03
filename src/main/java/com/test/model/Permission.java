package com.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Permission {
    private Integer perid;

    private String pername;

    private Integer pid;

    private String permission;
    private String url;
    private String router;
    private List<Permission> list = new ArrayList<>();

    }