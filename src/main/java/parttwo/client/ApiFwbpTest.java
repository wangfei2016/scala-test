package parttwo.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import parttwo.common.Taskflow;
import parttwo.component.Fwjd;
import parttwo.component.FwjdAction;
import parttwo.component.FwjdHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * API服务编排测试类.
 *
 * @author wang_fei
 * @since 2022/5/25 17:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = parttwo.Application.class)
public class ApiFwbpTest {

    @Autowired
    private Map<String, FwjdAction> fwjdAction = new ConcurrentHashMap<>();

    @Test
    public void test() {
        // 初始化各节点的执行对象(data to handler)
        List<FwjdHandler> list = new ArrayList<>();
        getVOList().forEach(t -> {
            FwjdHandler handler = new FwjdHandler();
            handler.setTask(t);
            list.add(handler);
        });
        // 通过各节点的执行对象构建任务流，然后运行任务流
        Taskflow taskflow = new Taskflow(list);
        list.forEach(t -> {
            System.out.print(t.getTask().getCjsd() + "*");
        });
        System.out.println();
        taskflow.run(5);

    }

    // 通过服务编排主键在数据库查询服务节点集合
    private List<Fwjd> getVOList() {
        List<Fwjd> list = new ArrayList<>();
        Fwjd f1 = new Fwjd();
        f1.setId("0");
        f1.setLx("0");
        f1.setMc("start");
        Fwjd f2 = new Fwjd();
        f2.setId("1");
        f2.setLx("2");
        f2.setFid(Arrays.asList("0", "4"));
        f2.setMc("Activity1");
        Fwjd f3 = new Fwjd();
        f3.setId("4");
        f3.setLx("2");
        f3.setFid(Arrays.asList("0"));
        f3.setMc("Activity4");

        Fwjd ff = new Fwjd();
        ff.setId("10");
        ff.setLx("1");
        ff.setFid(Arrays.asList("1"));
        ff.setMc("我是条件1");

        Fwjd f4 = new Fwjd();
        f4.setId("2");
        f4.setLx("2");
        f4.setFid(Arrays.asList("10"));
        f4.setMc("Activity2");
        Fwjd f5 = new Fwjd();
        f5.setId("3");
        f5.setLx("2");
        f5.setFid(Arrays.asList("10"));
        f5.setMc("Activity3");

        Fwjd f6 = new Fwjd();
        f6.setId("5");
        f6.setLx("2");
        f6.setFid(Arrays.asList("2", "3", "4"));
        f6.setMc("Activity5");
        Fwjd f7 = new Fwjd();
        f7.setId("6");
        f7.setLx("3");
        f7.setFid(Arrays.asList("5"));
        f7.setMc("end");
        list.add(f1);
        list.add(f2);
        list.add(f3);
        list.add(f4);
        list.add(f5);
        list.add(f6);
        list.add(f7);
        list.add(ff);
        return list;
    }
}
