package parttwo.common;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 任务处理器.
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
    protected List<? extends TaskHandler> nextList;

    // 处理请求
    public abstract Object handleRequest(Object param);

}
