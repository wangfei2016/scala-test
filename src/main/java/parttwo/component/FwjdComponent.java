package parttwo.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 请对类注释说明.
 *
 * @author wang_fei
 * @since 2022/5/30 18:05
 */
@Component
public class FwjdComponent {

    /**
     * 节点服务名前缀
     */
    private static final String SERVICE_NAME = "fwjdAction";

    @Autowired
    private Map<String, FwjdAction> fwjdAction = new ConcurrentHashMap<>();

    @NotNull
    public FwjdAction getComponent(String key) {
        return fwjdAction.get(SERVICE_NAME + key);
    }
}
