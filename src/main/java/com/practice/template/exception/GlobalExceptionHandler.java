package com.practice.template.exception;

import com.practice.template.dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.practice.template.constant.ApplicationConstant.MESSAGE;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler({SearchNotException.class})
	public HttpEntity<ErrorResponseDto> baseHandler(Exception e) {
		ErrorResponseDto errorResponseDto = new ErrorResponseDto();
		errorResponseDto.setMessage(e.getMessage());
		MultiValueMap<String, String> multiValueMap = new HttpHeaders();
		multiValueMap.add(MESSAGE, e.getMessage());
		return new ResponseEntity<>(errorResponseDto, multiValueMap, HttpStatus.BAD_REQUEST);
	}


}

