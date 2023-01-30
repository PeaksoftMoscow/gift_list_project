package com.peaksoft.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
@Getter
@Setter
public class Mail {

    private String from;
    private String to;
    private String subject;
    private Map<String,Object> model;
}
