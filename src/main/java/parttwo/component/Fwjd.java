package parttwo.component;

import lombok.Getter;
import lombok.Setter;
import parttwo.common.Task;

/**
 * 服务节点.
 *
 * @author wang_fei
 * @since 2022/5/27 11:25
 */
@Getter
@Setter
public class Fwjd extends Task {

    private String mc;

    private String ms;

    // 节点类型（0开始、1服务、2分支、3结束）
    private String lx;
}
