package parttwo.service;

import java.util.Map;

/**
 * ApiDyService.
 *
 * @author wang_fei
 * @since 2022/5/25 15:35
 */
public interface ApiDyService {

//    Object getData(ApiDyPo po);

    Object getData(Map<String, Object> param);
}
