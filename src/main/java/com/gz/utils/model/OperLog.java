package com.gz.utils.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperLog {

    private static final long serialVersionUID = 1L;

    /**
     * 日志主键
     */
    private Long operId;

    /**
     * 操作模块
     */
    private String title;

    /**
     * 请求方法
     */

    private String method;


    /**
     * 请求参数
     */

    private String operParam;

    /**
     * 返回参数
     */

    private String operResult;

    /**
     * 操作状态（0正常 1异常）
     */

    private Integer status;

    /**
     * 错误消息
     */

    private String errorMsg;

    /**
     * 操作时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operTime;

    /**
     * 消耗时间
     */

    private Long costTime;

}
