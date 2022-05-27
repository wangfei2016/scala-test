package parttwo.component;

import org.apache.commons.collections.CollectionUtils;
import parttwo.common.TaskHandler;

/**
 * FwxxHandler.
 *
 * @author wang_fei
 * @since 2022/5/27 13:11
 */
public class FwxxHandler extends TaskHandler<Fwxx> {

    @Override
    public Object handleRequest(Object param) {
        //执行当前服务
        System.out.println(getTask().getMc() + "-->" + getTask().getCjsz());
        param = (Integer) param + 1;
        System.out.println(">>>>>>>>>>>>");
        //执行后继任务
        if (CollectionUtils.isNotEmpty(nextList)) {
            for (TaskHandler taskHandler : getNextList()) {
                taskHandler.handleRequest(param);
            }
        }
        return param;
    }

}
