package parttwo.common;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import parttwo.component.Fwxx;
import parttwo.component.FwxxHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TaskGroup.
 *
 * @author wang_fei
 * @since 2022/5/27 11:29
 */
@Slf4j
@Getter
@Setter
public class TaskGroup {

    private TaskHandler head;

    public TaskGroup(List<TaskHandler> list) {
        List<TaskHandler> heads =
                list.stream().filter(handler -> null == handler.getTask().getFid()).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(heads) || heads.size() > 1) {
            log.error("构造器TaskGroup执行失败，传入参数不合法");
            return;
        }
        this.head = heads.get(0);
        head.setNextList(list.stream().filter(handler -> null != handler.getTask().getFid() && handler.getTask().getFid()
                .contains(head.getTask().getId())).collect(Collectors.toList()));
        this.setNextList(list, head);
    }

    private void setNextList(List<TaskHandler> list, TaskHandler current) {
        if (CollectionUtils.isEmpty(list) || null == current || CollectionUtils.isEmpty(current.getNextList())) {
            return;
        }
        List<TaskHandler> nextList = current.getNextList();
        for (TaskHandler handler : nextList) {
            if (null != handler.getTask().getSfzd() && handler.getTask().getSfzd()) {
                continue;
            }
            handler.setNextList(list.stream().filter(t -> null != t.getTask().getFid() && t.getTask().getFid()
                    .contains(handler.getTask().getId())).collect(Collectors.toList()));
            setNextList(list, handler);
        }
    }

    public static void main(String[] args) {
        List<TaskHandler> list = new ArrayList<>();
        getVOList().forEach(t -> {
            FwxxHandler handler = new FwxxHandler();
            handler.setTask(t);
            list.add(handler);
        });
        TaskGroup taskGroup = new TaskGroup(list);
        System.out.println(taskGroup.toString());
        taskGroup.getHead().handleRequest(5);

    }

    private static List<Fwxx> getVOList() {
        List<Fwxx> list = new ArrayList<>();
        Fwxx f1 = new Fwxx();
        f1.setId("0");
        f1.setMc("start");
        Fwxx f2 = new Fwxx();
        f2.setId("1");
        f2.setFid(Arrays.asList("0"));
        f2.setMc("Activity1");
        Fwxx f3 = new Fwxx();
        f3.setId("4");
        f3.setFid(Arrays.asList("0"));
        f3.setMc("Activity4");

        Fwxx f4 = new Fwxx();
        f4.setId("2");
        f4.setFid(Arrays.asList("1"));
        f4.setMc("Activity2");
        Fwxx f5 = new Fwxx();
        f5.setId("3");
        f5.setFid(Arrays.asList("1"));
        f5.setMc("Activity3");

        Fwxx f6 = new Fwxx();
        f6.setId("5");
        f6.setFid(Arrays.asList("2", "3", "4"));
        f6.setMc("Activity5");
        Fwxx f7 = new Fwxx();
        f7.setId("6");
        f7.setFid(Arrays.asList("5"));
        f7.setMc("end");
        f7.setSfzd(true);
        list.add(f1);
        list.add(f2);
        list.add(f3);
        list.add(f4);
        list.add(f5);
        list.add(f6);
        list.add(f7);
        return list;
    }
}
