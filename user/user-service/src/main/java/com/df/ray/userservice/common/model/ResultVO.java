package com.df.ray.userservice.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ResultVO 响应结果模型
 *
 * @author ray
 * @date 2021/12/23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> implements Serializable {
    private boolean status;
    private String msg;
    private T data;

    public ResultVO(boolean status, T data) {
        this.status = status;
        this.data = data;
    }

    public ResultVO(boolean status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ResultVO<T> success(T data) {
        return new ResultVO<T>(true, data);
    }

    public ResultVO<T> fail(String msg) {
        return new ResultVO<T>(false, msg);
    }
}
