package parttwo.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 任务流.
 *
 * @author wang_fei
 * @since 2022/5/27 11:29
 */
@Slf4j
public class Taskflow<T extends TaskHandler> {

    private Map<Integer, List<T>> data;

    public Taskflow(List<T> list) {
        List<T> heads =
                list.stream().filter(handler -> null == handler.getTask().getFid()).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(heads) || heads.size() > 1) {
            log.error("构造器Taskflow执行失败，传入参数不合法");
            return;
        }
        //
        T head = heads.get(0);
        head.getTask().setCjsd(0);
        head.setNextList(list.stream().filter(handler -> null != handler.getTask().getFid() && handler.getTask().getFid()
                .contains(head.getTask().getId())).collect(Collectors.toList()));
        //
        data = new HashMap<>();
        data.put(0, Collections.singletonList(head));
        this.buidData(list, head, data);
        System.out.println("");
    }

    private void buidData(List<T> list, TaskHandler current, Map<Integer, List<T>> data) {
        if (CollectionUtils.isEmpty(list) || null == current || CollectionUtils.isEmpty(current.getNextList())) {
            return;
        }
        List<T> nextList = current.getNextList();
        for (T handler : nextList) {
            handler.getTask().setCjsd(Math.max(handler.getTask().getCjsd(), current.getTask().getCjsd() + 1));
            setData(data, handler);
            if (null != handler.getTask().getSfzd() && handler.getTask().getSfzd()) {
                continue;
            }
            handler.setNextList(list.stream().filter(t -> null != t.getTask().getFid() && t.getTask().getFid()
                    .contains(handler.getTask().getId())).collect(Collectors.toList()));
            buidData(list, handler, data);
        }
    }

    private void setData(Map<Integer, List<T>> data, T handler) {
        int index = handler.getTask().getCjsd();
        List<T> handerList = data.get(index);
        if (null == handerList) {
            handerList = new ArrayList<>();
            handerList.add(handler);
            data.put(index, handerList);
        } else {
            if (!handerList.contains(handler)) {
                handerList.add(handler);
            }
        }
    }

    public void run(Object param) {
        TaskGroup<T> group = new TaskGroup<>(data.get(0));
        for (Map.Entry<Integer, List<T>> entry : data.entrySet()) {
            if (0 == entry.getKey()) {
                continue;
            }
            List<T> data = new ArrayList<>();
            for (T t : entry.getValue()) {
                if (t.getTask().getCjsd() != entry.getKey()) {
                    continue;
                }
                data.add(t);
            }
            group.add(data);
        }
        for (Map.Entry<Integer, List<T>> entry : data.entrySet()) {
            for (T t : entry.getValue()) {
                if (t.getTask().getCjsd() != entry.getKey()) {
                    continue;
                }
                t.handleRequest(5);
            }
            System.out.println(">>>>>>>>>>>>" );
            System.out.println();
        }
        System.out.println();
    }

    class TaskGroup<T extends TaskHandler> {

        List<T> data;

        TaskGroup<T> next;

        public TaskGroup(List<T> data) {
            this.data = data;
        }

        // 添加新的结点
        public void add(List<T> data) {
            if (null == this.next)
                this.next = new TaskGroup(data);
            else
                this.next.add(data);

        }

        // 打印的结点
        public void print() {
            System.out.print("<-->");
            this.data.forEach(d -> {
                System.out.print(d.getTask().getId());
            });
            if (this.next != null) {
                this.next.print();
            }
            System.out.print("<-->");
        }
    }
}

