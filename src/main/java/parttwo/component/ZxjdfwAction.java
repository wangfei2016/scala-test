package parttwo.component;

import org.springframework.stereotype.Component;

/**
 * 执行API服务Action.
 *
 * @author wang_fei
 * @since 2022/5/30 17:39
 */
@Component("fwjdAction2")
public class ZxjdfwAction implements FwjdAction {

    @Override
    public void prepare() {
        System.out.println("prepare...");
    }

    @Override
    public void execute() {
        System.out.println("执行API服务Action");
    }
}
