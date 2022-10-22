package com.amirscode.clickup.service;

import com.amirscode.clickup.entity.Comment;
import com.amirscode.clickup.entity.User;
import com.amirscode.clickup.payload.CommentDTO;
import com.amirscode.clickup.repository.CommentRepository;
import com.amirscode.clickup.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service

public class CommentServiceImpl implements CommentService{

    final CommentRepository commentRepository;
    final TaskRepository taskRepository;

    @Override
    public HttpEntity<?> writeComment(CommentDTO commentDTO) {
        var task = taskRepository.findById(commentDTO.getTaskId()).orElseThrow(ResourceNotFoundException::new);
        Comment comment = new Comment(commentDTO.getText(), task);
        commentRepository.save(comment);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public HttpEntity<?> readComment(UUID id) {

        try {
            var commentList = commentRepository.findAllByTaskId(id);
            return ResponseEntity.ok().body(commentList);

        }catch (ResourceNotFoundException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    public HttpEntity<?> editComment(Long id, String text, User currentUser) {

        var comment = commentRepository.findById(id).orElseThrow();
        if(comment.getCreatedBy().getId().equals(currentUser.getId())){
            comment.setText(text);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @Override
    public HttpEntity<?> deleteComment(Long id, User currentUser) {
        var comment = commentRepository.findById(id).orElseThrow();
        if(comment.getCreatedBy().getId().equals(currentUser.getId())){
           commentRepository.delete(comment);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
