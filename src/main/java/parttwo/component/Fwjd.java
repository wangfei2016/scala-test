package parttwo.component;

import lombok.Getter;
import lombok.Setter;
import parttwo.common.FlowNode;

/**
 * 服务编排节点.
 *
 * @author wang_fei
 * @since 2022/5/27 11:25
 */
@Getter
@Setter
public class Fwjd extends FlowNode {

    private String mc;

    private String bm;

    // 节点类型（0开始、1分支、2API、3结束）
    private String jdlx;

    // API服务
    private String apiId;
}
