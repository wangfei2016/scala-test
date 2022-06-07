package parttwo.common;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 节点树（流程节点过渡到流程图的中间形态的数据结构，主要用于计算节点的层级深度）.
 *
 * @author wang_fei
 * @since 2022/6/1 9:34
 */
@Getter
@Setter
@Slf4j
public class NodeTree<T extends FlowNode> {

    /**
     * root对象
     */
    private T root;

    /**
     * NodeTree构造器
     *
     * @param nodes 流程节点集合
     */
    public NodeTree(List<T> nodes) {
        List<T> start =
                nodes.stream().filter(node -> null == node.getFids()).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(start) || start.size() > 1) {
            log.error("NodeTree构造器执行失败，传入参数不合法");
            return;
        }
        // 创建root对象
        this.root = start.get(0);
        // 设置root对象的层级深度
        root.setCjsd(0);
        // 设置root对象的直接子节点
        setChildren(nodes, root);
        // 根据节点集合和root对象构建整棵节点树
        this.createChildren(nodes, root);
    }

    /**
     * 根据节点集合和父子节点构建整棵节点树
     */
    private void createChildren(List<T> nodes, T parent) {
        if (CollectionUtils.isEmpty(nodes) || null == parent
                || CollectionUtils.isEmpty(parent.getChildren())) {
            return;
        }
        // 获取子节点的集合并创建其父节点的其他所有节点
        List<T> children = parent.getChildren();
        for (T child : children) {
            setCjsd(child, parent);
            setChildren(nodes, child);
            if (CollectionUtils.isEmpty(child.getChildren())) {
                continue;
            }
            createChildren(nodes, child);
        }
    }

    /**
     * 设置树节点的层级深度
     */
    private void setCjsd(T child, T parent) {
        child.setCjsd(Math.max(child.getCjsd(), parent.getCjsd() + 1));
    }

    /**
     * 设置树节点的直接子节点
     */
    private void setChildren(List<T> nodes, T parent) {
        parent.setChildren(nodes.stream().filter(node -> StringUtils.isNotBlank(node.getFids())
                && Arrays.asList(node.getFids().split(",")).contains(parent.getId())).collect(Collectors.toList()));
    }

}
