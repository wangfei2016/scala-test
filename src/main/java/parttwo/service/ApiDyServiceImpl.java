package parttwo.service;

import java.util.Map;

/**
 * ApiDyServiceImpl.
 *
 * @author wang_fei
 * @since 2022/5/25 16:01
 */
public class ApiDyServiceImpl implements ApiDyService {

    @Override
    public Object getData(Map<String, Object> param) {
        System.out.println("param=" + param.get("value").toString());
        int result = (int) (Math.random() * Integer.valueOf(param.get("value").toString()) + 1);
        System.out.println("getData=" + result);
        return result;
    }
}
