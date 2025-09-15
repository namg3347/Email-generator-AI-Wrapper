package com.redhat.extensions.emailgeneratorapi.models;

import lombok.Data;

@Data
public class EmailRequest {

    private String emailContent;
    private String tone;
    private String remarks;
}
