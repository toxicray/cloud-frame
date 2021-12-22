package com.df.ray.userservice.common.exception;

import lombok.NoArgsConstructor;

/**
 * WarnException
 *
 * @author ray
 * @date 2021/12/23
 */
@NoArgsConstructor
public class WarnException extends RuntimeException{

    public WarnException(String message) {
        super(message);
    }
}
