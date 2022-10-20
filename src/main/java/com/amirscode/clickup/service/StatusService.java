package com.amirscode.clickup.service;

import com.amirscode.clickup.payload.StatusDTO;
import org.springframework.http.HttpEntity;

public interface StatusService {


    HttpEntity<?> addStatus(StatusDTO statusDTO);

    HttpEntity<?> editStatus(Long id, StatusDTO statusDTO);

    HttpEntity<?> deleteStatus(Long id);

    HttpEntity<?> getStatus(StatusDTO statusDTO);
}
