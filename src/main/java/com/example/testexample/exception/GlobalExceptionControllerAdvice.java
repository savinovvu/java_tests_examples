package com.example.testexample.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionControllerAdvice {

  private final Map<Class<?>, HttpStatus> exceptionStatusMap = new HashMap<>();

  public GlobalExceptionControllerAdvice() {
    exceptionStatusMap.put(ApiException.class, UNPROCESSABLE_ENTITY);
    exceptionStatusMap.put(ModelNotFound.class, NOT_FOUND);
  }


  @ExceptionHandler(Throwable.class)
  public String handleCommonException(
      HttpServletRequest request,
      Model model,
      Throwable t) {
    log.error("error is going on:", t);
    HttpStatus status = exceptionStatusMap.getOrDefault(t.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);

    if (status == HttpStatus.INTERNAL_SERVER_ERROR) {

      log.warn(
          "request to {}; {}: {}, responding with <{} {}>",
          request.getRequestURI(),
          t.getClass().getCanonicalName(),
          t.getMessage(),
          status.value(),
          status.getReasonPhrase(),
          t
      );
    } else {
      log.warn(
          "request to {}; {}: {}, responding with <{} {}>",
          request.getRequestURI(),
          t.getClass().getCanonicalName(),
          t.getMessage(),
          status.value(),
          status.getReasonPhrase()
      );
    }

    String title = "Error " + status.value();
    model.addAttribute("heading", title);
    model.addAttribute("description", message(t));
    return "pages/common/error";

  }

  private Object message(Throwable t) {
    if (exceptionStatusMap.containsKey(t.getClass())) {
      return t.getMessage();
    } else {
      return "Internal Server Error";
    }
  }


}
