package parttwo;

import java.util.Map;

/**
 * TaskHandler.
 *
 * @author wang_fei
 * @since 2022/5/25 15:27
 */
public abstract class TaskHandler {

    protected TaskHandler next;

    // 判断条件
    public boolean judgeCondition(Map<String, Object> param) {
        return true;
    }
    // 处理请求
    public abstract Object handleRequest(Map<String, Object> param);
    // 设置后继任务
    public void setNextTaskHandler(TaskHandler next) {
        this.next = next;
    }
    // 获取后继参数
    public abstract Map<String, Object> getNextParam(Object result);
}
