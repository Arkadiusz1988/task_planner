package workshop.task_planner.entities;


import lombok.Data;
import workshop.task_planner.dto.CommentDto;

import javax.persistence.*;

@Entity
@Data
public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Task task;

    private String content;

    public Comment(Task task, String content) {
        this.task = task;
        this.content = content;
    }

    public Comment() {
    }

    public CommentDto toCommentDto() {
        CommentDto commentDto = new CommentDto();
        commentDto.setContent(content);
        return commentDto;
    }


}
