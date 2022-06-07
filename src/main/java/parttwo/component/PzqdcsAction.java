package parttwo.component;

import org.springframework.stereotype.Component;

/**
 * 配置启动参数Action.
 *
 * @author wang_fei
 * @since 2022/5/30 17:28
 */
@Component("fwjdAction0")
public class PzqdcsAction implements FwjdAction {

    @Override
    public void prepare() {
        System.out.println("prepare...");
    }

    @Override
    public void execute() {
        System.out.println("配置启动参数Action");
    }
}
