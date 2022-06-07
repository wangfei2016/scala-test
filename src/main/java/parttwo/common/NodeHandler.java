package parttwo.common;

import lombok.Getter;
import lombok.Setter;

/**
 * 节点处理器.
 *
 * @author wang_fei
 * @since 2022/5/27 11:04
 */
@Getter
@Setter
public abstract class NodeHandler<T extends FlowNode> {

    /**
     * 节点对象
     */
    private T flowNode;

    /**
     * 处理请求
     */
    public abstract void handleRequest();

}
