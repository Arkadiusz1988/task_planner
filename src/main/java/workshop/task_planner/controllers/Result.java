package workshop.task_planner.controllers;


import lombok.Data;


//answer if action was ok
@Data
public class Result {

    private String code;
    private String message;
    private Object data;

    public Result(String code) {
        this.code = code;
    }

    public Result() {
    }

    public static Result ok(Object data) {
        Result result = new Result("200");
        result.setData(data);
        result.setMessage("Operacja powiodła się!");
        return result;
    }


}