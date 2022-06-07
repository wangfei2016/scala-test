package parttwo.component;

import parttwo.common.ApplicationContextGetBeanHelper;
import parttwo.common.NodeHandler;

/**
 * 服务编排节点处理器.
 *
 * @author wang_fei
 * @since 2022/5/27 13:11
 */
public class FwjdHandler extends NodeHandler<Fwjd> {

    @Override
    public void handleRequest() {
        System.out.println(getFlowNode().getMc() + "-->" + getFlowNode().getCjsd());
        Object actor = ApplicationContextGetBeanHelper.getBean("FwjdActionContext");
        FwjdActionContext context = (FwjdActionContext) actor;
        context.service(getFlowNode().getJdlx());
    }

}
