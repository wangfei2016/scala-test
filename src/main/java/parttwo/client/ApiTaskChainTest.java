package parttwo.client;

import parttwo.ApiTaskHandler;
import parttwo.TaskHandler;
import scala.annotation.meta.param;

import java.util.HashMap;
import java.util.Map;

/**
 * ApiTaskChainTest.
 *
 * @author wang_fei
 * @since 2022/5/25 17:52
 */
public class ApiTaskChainTest {

    public static void main(String[] args) {
        TaskHandler t1 = new ApiTaskHandler();
        TaskHandler t2 = new ApiTaskHandler();
        TaskHandler t3 = new ApiTaskHandler();

        t1.setNextTaskHandler(t2);
        Map<String, Object> p2 = buildMap("condition", "even");
        p2.put("even", "Y");
        t2.judgeCondition(p2);
        t2.setNextTaskHandler(t3);
        Map<String, Object> p3 = buildMap("condition", "max");
        p3.put("max", "5");
        t3.judgeCondition(p3);
        Object result = t1.handleRequest(buildMap("value", "100"));
        System.out.println(result);
    }

    private static Map<String, Object> buildMap(String key, Object val) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, val);
        return map;
    }
}
