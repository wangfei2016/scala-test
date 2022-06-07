package parttwo.common;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 节点工厂类（以流程节点为原材料，可生产节点树、流程图）.
 *
 * @author wang_fei
 * @since 2022/6/6 14:17
 */
@Component
public class NodeFactory<T extends FlowNode, H extends NodeHandler> {

    public NodeTree<T> createNodeTree(List<T> list) {
        NodeTree<T> tree = new NodeTree(list);
        return tree;
    }

    public FlowChart<T, H> createFlowChart(List<T> list, Class<H> clazz) {
        // 创建节点树计算出每个节点的层级深度
        createNodeTree(list);
        // 节点层级深度与对应节点集合的映射表
        Map<Integer, List<T>> map = list.stream().collect(Collectors.groupingBy(FlowNode::getCjsd));
        // 创建流程图
        FlowChart<T, H> flowChart = new FlowChart<>(map.get(0), clazz);
        for (Map.Entry<Integer, List<T>> entry : map.entrySet()) {
            // key为0表示head，head在构造器中已经初始化，所以要过滤掉key为0的数据
            if (0 == entry.getKey()) {
                continue;
            }
            List<T> nodes = new ArrayList<>();
            for (T node : entry.getValue()) {
                if (node.getCjsd() != entry.getKey()) {
                    continue;
                }
                nodes.add(node);
            }
            flowChart.add(nodes);
        }
        return flowChart;
    }
}
