package parttwo.common;

import java.util.List;

/**
 * 流程图（由各层级节点集合构成的职责链）.
 *
 * @author wang_fei
 * @since 2022/6/6 9:57
 */
public class FlowChart<T extends FlowNode, H extends NodeHandler> {

    private List<T> nodes;

    private FlowChart<T, H> next;

    private Class<H> handlerClazz;

    public FlowChart(List<T> nodes, Class<H> handlerClazz) {
        this.nodes = nodes;
        this.handlerClazz = handlerClazz;
    }

    public void add(List<T> nodes) {
        if (null == this.next) {
            this.next = new FlowChart(nodes, handlerClazz);
        } else {
            this.next.add(nodes);
        }
    }

    public void run() {
        System.out.println();
        System.out.print("<--");
        this.nodes.forEach(node -> {
            H handler = createNodeHandler(node, handlerClazz);
            if (null != handler) {
                handler.handleRequest();
            }
        });
        if (null != this.next) {
            this.next.run();
        }
    }

    /**
     * 创建节点处理器实例
     *
     * @param flowNode 流程节点
     * @param clazz    节点处理器的class对象
     * @return 节点处理器实例
     */
    private H createNodeHandler(T flowNode, Class<H> clazz) {
        H handler = null;
        try {
            handler = clazz.newInstance();
            handler.setFlowNode(flowNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return handler;
    }

}
