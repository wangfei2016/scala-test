package parttwo.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import parttwo.common.ApplicationContextGetBeanHelper;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务编排节点操作接口.
 *
 * @author wang_fei
 * @since 2022/5/30 18:05
 */
@Component
public class FwjdActionContext {

    /**
     * 节点服务名前缀
     */
    private static final String SERVICE_NAME = "fwjdAction";

    @Autowired
    private Map<String, FwjdAction> fwjdAction = new ConcurrentHashMap<>();

    public FwjdAction getAction(String key) {
        return fwjdAction.get(SERVICE_NAME + key);
    }

    public void service(String key) {
        FwjdAction action = getAction(key);
        action.prepare();
        action.execute();
    }
}
