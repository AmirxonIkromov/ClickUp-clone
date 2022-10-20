package com.amirscode.clickup.service;

import com.amirscode.clickup.entity.Tag;
import com.amirscode.clickup.payload.TagDTO;
import com.amirscode.clickup.repository.TagRepository;
import com.amirscode.clickup.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service

public class TagServiceImpl implements TagService{

    final ModelMapper mapper;
    final TagRepository tagRepository;
    final WorkspaceRepository workspaceRepository;

    @Override
    public HttpEntity<?> addTag(TagDTO tagDTO) {

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Tag tag = mapper.map(tagDTO, Tag.class);
        tagRepository.save(tag);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public HttpEntity<?> editTag(Long id, TagDTO tagDTO) {
        var tag = tagRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        tag = mapper.map(tagDTO, Tag.class);
        tagRepository.save(tag);

        return ResponseEntity.ok().build();
    }

    @Override
    public HttpEntity<?> deleteTag(Long id) {
        tagRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
