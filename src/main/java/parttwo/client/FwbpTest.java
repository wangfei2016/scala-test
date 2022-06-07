package parttwo.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import parttwo.common.FlowChart;
import parttwo.common.NodeFactory;
import parttwo.component.Fwjd;
import parttwo.component.FwjdHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务编排测试类.
 *
 * @author wang_fei
 * @since 2022/5/25 17:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = parttwo.Application.class)
public class FwbpTest {

    @Autowired
    private NodeFactory nodeFactory;

    @Test
    public void test() {
        FlowChart<Fwjd, FwjdHandler> flowChart = nodeFactory.createFlowChart(getVOList(),
                FwjdHandler.class);
        flowChart.run();
    }

    // 通过服务编排主键在数据库查询服务节点集合
    private List<Fwjd> getVOList() {
        List<Fwjd> list = new ArrayList<>();
        Fwjd f1 = new Fwjd();
        f1.setId("0");
        f1.setJdlx("0");
        f1.setMc("start");
        Fwjd f2 = new Fwjd();
        f2.setId("1");
        f2.setJdlx("2");
        f2.setFids("0,4");
        f2.setMc("Activity1");
        Fwjd f3 = new Fwjd();
        f3.setId("4");
        f3.setJdlx("2");
        f3.setFids("0");
        f3.setMc("Activity4");

        Fwjd ff = new Fwjd();
        ff.setId("10");
        ff.setJdlx("1");
        ff.setFids("1");
        ff.setMc("我是条件1");

        Fwjd f4 = new Fwjd();
        f4.setId("2");
        f4.setJdlx("2");
        f4.setFids("10");
        f4.setMc("Activity2");
        Fwjd f5 = new Fwjd();
        f5.setId("3");
        f5.setJdlx("2");
        f5.setFids("10");
        f5.setMc("Activity3");

        Fwjd f6 = new Fwjd();
        f6.setId("5");
        f6.setJdlx("2");
        f6.setFids("2,3,4");
        f6.setMc("Activity5");
        Fwjd f7 = new Fwjd();
        f7.setId("6");
        f7.setJdlx("3");
        f7.setFids("5");
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
