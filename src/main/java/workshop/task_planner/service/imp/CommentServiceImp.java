package workshop.task_planner.service.imp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workshop.task_planner.dto.CommentDto;
import workshop.task_planner.entities.Comment;
import workshop.task_planner.repositories.CommentRepository;
import workshop.task_planner.service.BaseService;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CommentServiceImp implements BaseService <CommentDto,Long> {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImp(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    @Override
    public CommentDto save(CommentDto commentDto){
        commentRepository.save(commentDto.toComment());
        return commentDto;
    }

    @Override
    public CommentDto update(CommentDto commentDto,Long id){
        Comment comment = commentRepository.getOne(id);
        comment.setContent(commentDto.getContent());
        comment.setTask(commentDto.getTask());
        commentRepository.save(comment);
        return comment.toCommentDto();
    }

    @Override
    public CommentDto find(Long id) {
        Comment comment = commentRepository.getOne(id);
        if (Objects.nonNull(comment)) {
            return comment.toCommentDto();
        }
        return null;
    }

    @Override
    public Boolean remove(Long id) {
        Comment comment = commentRepository.getOne(id);
        commentRepository.delete(comment);
        return true;
    }

    @Override
    public Collection<CommentDto> getAll() {
        return commentRepository
                .findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(Comment::toCommentDto)
                .collect(Collectors.toList());
    }
}
