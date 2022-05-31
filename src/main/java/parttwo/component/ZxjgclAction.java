package parttwo.component;

import org.springframework.stereotype.Component;

/**
 * 执行结果处理Action.
 *
 * @author wang_fei
 * @since 2022/5/30 17:41
 */
@Component("fwjdAction3")
public class ZxjgclAction implements FwjdAction {

    @Override
    public void execute() {
        System.out.println("执行结果处理Action");
    }
}
