package com.maskun.moard.service;

import com.maskun.moard.domain.post.Post;
import com.maskun.moard.domain.post.PostJpaRepository;
import com.maskun.moard.web.dto.PostSaveDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {
    private final PostJpaRepository repository;

    /** DB에 post를 저장한다.
     * @param dto 가 null 이면 예외가 발생하므로 유효성 검사를 한다.
     * @return 저장된 Entity의 Id를 반환한다.
     * */
    @Transactional
    public Long savePost(PostSaveDto dto){
        Post postSaved;
        postSaved = repository.save(dto.toEntity());
        return postSaved.getPostId();
    }

    @Transactional
    public Long deletePost(Long postId) {
        Post postFound;
        /*SimpleJpaRepository 의 method .delete()는 수행전 Entity를 DB에서 확인하고 없는 경우 아무것도 수행하지 않는다.
        * 따라서 개발자 입장에서 이게 정상적으로 수행됬는지 확실하게 알기가 힘들었다. 따라서 findyById가 한번 중복되지만
        * 유효성검사를 위해 선실행 후 있는 경우만 delete를 실행하도록 유도했다.
         */
        postFound = repository.findById(postId).orElse(null);
        if(postFound != null){
            repository.delete(postFound);
            return postId;
        }else{
            log.error("존재하지 않는 엔티티를 삭제하려고 시도했습니다.");
            return null;
        }
    }

}
