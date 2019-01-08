package workshop.task_planner.dto;


import lombok.Data;
import workshop.task_planner.entities.Comment;
import workshop.task_planner.entities.Task;

@Data
public class CommentDto {

    private Long id;

    private String content;

    private Task task;

    public CommentDto(Long id, String content, Task task) {
        this.id = id;
        this.content = content;
        this.task = task;
    }

    public CommentDto() {
    }

    public Comment toComment() {
        Comment comment = new Comment();
        comment.setId(id);
        comment.setContent(content);
        comment.setTask(task);
        return comment;
    }

}
