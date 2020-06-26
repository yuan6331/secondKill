package com.gdut.controller;

import com.gdut.util.BaseException;
import com.gdut.util.IResponseEnum;

public class BusinessException extends BaseException {

	private static final long serialVersionUID = 1L;

	public BusinessException(IResponseEnum responseEnum, Object[] args, String message) {
		super(responseEnum, args, message);
	}

	public BusinessException(IResponseEnum responseEnum, Object[] args, String message, Throwable cause) {
		super(responseEnum, args, message, cause);
	}
}
