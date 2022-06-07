package parttwo.component;

import org.springframework.stereotype.Component;

/**
 * 分支判断开关Action.
 *
 * @author wang_fei
 * @since 2022/5/30 17:32
 */
@Component("fwjdAction1")
public class FzpdkgAction implements FwjdAction {

    @Override
    public void prepare() {
        System.out.println("prepare...");
    }

    @Override
    public void execute() { System.out.println("分支判断开关Action"); }

}
