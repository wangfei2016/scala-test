package parttwo;

import org.springframework.beans.factory.annotation.Autowired;
import parttwo.service.ApiDyService;
import parttwo.service.ApiDyServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * ApiTaskHandler.
 *
 * @author wang_fei
 * @since 2022/5/25 15:29
 */
public class ApiTaskHandler extends TaskHandler {

//    @Autowired
    ApiDyService apiDyService = new ApiDyServiceImpl();

    // 测试专用(条件)
    private static final String CONDITION = "condition";
    // 测试专用(数值)
    private static final String VALUE = "value";
    // 测试专用(偶数)
    private static final String EVEN = "even";
    // 测试专用(最大值)
    private static final String MAX = "max";

    @Override
    public boolean judgeCondition(Map<String, Object> param) {
        // 没有条件返回true
        if (null == param.get(CONDITION)) {
            return true;
        }
        String c = param.get(CONDITION).toString();
        // 条件值位空返回false
        if (null == param.get(VALUE)) {
            return false;
        }
        int v = Integer.valueOf(param.get(VALUE).toString());
        // 偶数条件
        if (EVEN.equals(c) && "Y".equals(param.get(EVEN))) {
            return v % 2 == 0;
        }
        // 最大值条件
        if (MAX.equals(c) && null != param.get(MAX)) {
            return v <= Integer.valueOf(param.get(MAX).toString());
        }
        return true;
    }

    @Override
    public Object handleRequest(Map<String, Object> param) {
        Object result = param;
        if (judgeCondition(param)) {
            result = apiDyService.getData(param);
            System.out.println("*******over*******");
        }
        if (null == next) {
            return result;
        }
        return next.handleRequest(getNextParam(result));
    }

    @Override
    public Map<String, Object> getNextParam(Object result) {
        Map<String, Object> nextParam = new HashMap<>();
        nextParam.put("value", result);
        return nextParam;
    }

}
