package com.amirscode.clickup.controller;

import com.amirscode.clickup.entity.User;
import com.amirscode.clickup.payload.CommentDTO;
import com.amirscode.clickup.security.CurrentUser;
import com.amirscode.clickup.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/comment")

public class CommentController {

    final CommentService commentService;

    @PostMapping
    public HttpEntity<?> writeComment(@Valid @RequestBody CommentDTO commentDTO){
        return commentService.writeComment(commentDTO);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> readComment(@PathVariable UUID id){
        return commentService.readComment(id);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editComment(@PathVariable Long id, @RequestParam String text, @CurrentUser User currentUser){
        return commentService.editComment(id, text, currentUser);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteComment(@PathVariable Long id, @CurrentUser User currentUser){
        return commentService.deleteComment(id, currentUser);
    }
}
