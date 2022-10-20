package com.amirscode.clickup.service;

import com.amirscode.clickup.payload.TagDTO;
import org.springframework.http.HttpEntity;

public interface TagService {

    HttpEntity<?> addTag(TagDTO tagDTO);

    HttpEntity<?> editTag(Long id, TagDTO tagDTO);

    HttpEntity<?> deleteTag(Long id);

}
