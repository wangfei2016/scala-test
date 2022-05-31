package parttwo.component;

import org.springframework.beans.factory.annotation.Autowired;
import parttwo.common.ApplicationContextGetBeanHelper;
import parttwo.common.TaskHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * FwxxHandler.
 *
 * @author wang_fei
 * @since 2022/5/27 13:11
 */
public class FwjdHandler extends TaskHandler<Fwjd> {

    @Override
    public Object handleRequest(Object param) {
        // 分支判断
        // 配置参数
        System.out.println(getTask().getMc() + "-->" + getTask().getCjsd());
        param = (Integer) param + 1;
        Object actor = ApplicationContextGetBeanHelper.getBean("FwjdComponent");
        FwjdComponent component = (FwjdComponent) actor;
        component.getComponent(getTask().getLx()).execute();
        return param;
    }

}
