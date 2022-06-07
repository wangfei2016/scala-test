package parttwo.common;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 流程节点（流程节点对象的数据库模型）.
 *
 * @author wang_fei
 * @since 2022/5/27 11:11
 */
@Getter
@Setter
public class FlowNode<T extends FlowNode> {

    /**
     * id
     */
    private String id;

    /**
     * 父id集合（多个用,分割）
     */
    private String fids;

    /**
     * 层级深度
     */
    private int cjsd;

    /**
     * 子节点集合
     */
    private List<T> children;
}
