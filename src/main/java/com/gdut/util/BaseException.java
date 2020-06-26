package com.gdut.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author gaoyuan 2020年6月16日 上午2:27:19
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * 错误代码
	 */
	public int code;

	/**
	 * 错误信息
	 */
	public String message;

	public BaseException(IResponseEnum responseEnum, Object[] args, String message2) {
		// TODO Auto-generated constructor stub
	}

	public BaseException(IResponseEnum responseEnum, Object[] args, String message2, Throwable cause) {
		// TODO Auto-generated constructor stub
	}

}
