package parttwo.service;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * TaskPo.
 *
 * @author wang_fei
 * @since 2022/5/25 15:57
 */
@Setter
@Getter
public class ApiDyPo {
    /**
     * 免密认证code
     */
    private String appCode;

    /**
     * API测试token
     */
    private String apiTestToken;

    /**
     * API真实调用token
     */
    private String apiToken;

    /**
     * sql查询Id
     */
    private String sqlId;

    /**
     * 用户调用地址
     */
    private String dz;

    /**
     * 页码
     */
    private Integer pageIndex = 1;

    /**
     * 页长
     */
    private Integer pageSize = 10;

    /**
     * 详细参数对象
     */
    private List list;

    /**
     * Json参数
     */
    private JSONObject jsonObj;

    /**
     * XML参数
     */
    private String xmlStr;

    /**
     * 请求头
     */
    private List headers;

    /**
     * api主表id
     */
    private String apiId;
}
