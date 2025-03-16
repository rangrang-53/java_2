package com.example.tokenlogin.service;

import com.example.tokenlogin.dto.BoardDeleteRequestDTO;
import com.example.tokenlogin.dto.BoardDetailResponseDTO;
import com.example.tokenlogin.mapper.BoardMapper;
import com.example.tokenlogin.model.Article;
import com.example.tokenlogin.model.Paging;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;
    private final FileService fileService;

    public List<Article> getBoardArticles(int page, int size) {
        int offset = (page - 1) * size; // 페이지는 1부터 시작 offset 계산
        return boardMapper.getArticles(
                Paging.builder()
                        .offset(offset)
                        .size(size)
                        .build()
        );
    }

    @Transactional
    public void saveArticle(String userId, String title, String content, MultipartFile file) {
        String path = null;
        if (!file.isEmpty()) {
            path = fileService.fileUpLoad(file);
        }

        boardMapper.saveArticle(
                Article.builder()
                        .title(title)
                        .content(content)
                        .userId(userId)
                        .filePath(path)
                        .build()
        );
    }

    public int getTotalArticleCnt() {
        return boardMapper.getArticleCnt();
    }

    public Article getBoardDetail(long id) {
        return boardMapper.getArticleById(id);
    }

    public Resource downloadFile(String fileName) {
        return fileService.downloadFile(fileName);
    }

    public void updateArticle(Long id, String title, String content, MultipartFile file, Boolean fileChanged,
                              String filePath) {
        String path = null;

        if (!file.isEmpty()) {
            path = fileService.fileUpLoad(file);
        }

        if(fileChanged) {
            fileService.deleteFile(filePath);
        } else {
            path = filePath;
        }

        boardMapper.updateArticle(
                Article.builder()
                        .id(id)
                        .title(title)
                        .content(content)
                        .filePath(path)
                        .build()
        );
    }

    public void deleteBoardById(long id, BoardDeleteRequestDTO requestDTO) {
        fileService.deleteFile(requestDTO.getFilePath());
        boardMapper.deleteBoardById(id);
    }
}