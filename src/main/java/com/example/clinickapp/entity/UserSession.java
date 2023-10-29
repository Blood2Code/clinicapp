package com.example.clinickapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSession {
    private String language;
    private Date activeSessionDate;
    private String sessionId;
    private String remoteAddress;
    private String osInfo;
}
