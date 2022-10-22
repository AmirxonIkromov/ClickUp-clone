package com.amirscode.clickup.service;

import com.amirscode.clickup.entity.User;
import com.amirscode.clickup.payload.CommentDTO;
import org.springframework.http.HttpEntity;

import java.util.UUID;

public interface CommentService {

    HttpEntity<?> writeComment(CommentDTO commentDTO);

    HttpEntity<?> readComment(UUID id);

    HttpEntity<?> editComment(Long id, String text, User currentUser);

    HttpEntity<?> deleteComment(Long id, User currentUser);
}
