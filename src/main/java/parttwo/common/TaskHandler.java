package parttwo.common;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * TaskHandler.
 *
 * @author wang_fei
 * @since 2022/5/27 11:04
 */
@Getter
@Setter
public abstract class TaskHandler<T extends Task> {

    @NotNull
    private T task;

    // 后继任务
    protected List<TaskHandler> nextList;

    // 设置后继任务
    public void setNextList(List<TaskHandler> nextList) {
        this.nextList = nextList;
    }
    public List<TaskHandler> getNextList() {
        return this.nextList;
    }


    // 处理任务请求
    public abstract Object handleRequest(Object param);

}
