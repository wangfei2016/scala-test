package parttwo.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * TaskTree.
 *
 * @author wang_fei
 * @since 2022/6/1 9:34
 */
@Slf4j
public class TaskTree<T extends Task> {

    public TaskTree(List<T> list) {
        List<T> heads =
                list.stream().filter(task -> null == task.getFid()).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(heads) || heads.size() > 1) {
            log.error("TaskTree构造器执行失败，传入参数不合法");
            return;
        }
        // 构建root对象
        T root = heads.get(0);
        // 设置root对象的直接子节点
        root.setChildren(list.stream().filter(task -> CollectionUtils.isNotEmpty(task.getFid()) && task.getFid()
                .contains(root.getId())).collect(Collectors.toList()));
        // 根据父子节点创建所有子节点
        this.createChildren(list, root);
    }

    /**
     * 创建父子节点的直接子节点
     */
    private void createChildren(List<T> list, T parent) {
        if (CollectionUtils.isEmpty(list) || null == parent || CollectionUtils.isEmpty(parent.getChildren())) {
            return;
        }
        List<T> children = (List<T>) parent.getChildren();
        for (T child : children) {
            order(child, parent);
            child.setChildren(list.stream().filter(task -> CollectionUtils.isNotEmpty(task.getFid()) && task.getFid()
                    .contains(child.getId())).collect(Collectors.toList()));
            if (CollectionUtils.isEmpty(child.getChildren())) {
                continue;
            }
            createChildren(list, child);
        }
    }

    private void order(T child, T parent) {
        child.setCjsd(Math.max(child.getCjsd(), parent.getCjsd() + 1));
    }
}
