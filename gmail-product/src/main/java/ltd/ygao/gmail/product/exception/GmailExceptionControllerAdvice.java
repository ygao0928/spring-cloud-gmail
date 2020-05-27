package ltd.ygao.gmail.product.exception;

import lombok.extern.slf4j.Slf4j;
import ltd.ygao.gmail.common.exception.BizCodeEnume;
import ltd.ygao.gmail.common.utils.R;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kevin
 * @version 1.0
 * @date 2020/5/26 10:48
 */
@Slf4j
@RestControllerAdvice(basePackages = "ltd.ygao.gmail.product.controller")
public class GmailExceptionControllerAdvice {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleVaildException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        Map<String, String> errorMap = new HashMap<>();
        bindingResult.getFieldErrors().forEach((item) -> {
            errorMap.put(item.getField(), item.getDefaultMessage());
        });
        log.error("数据校验出现异常：", e.getMessage(), e.getClass());
        return R.error(BizCodeEnume.VALID_EXCEPTION.getCode(),BizCodeEnume.VALID_EXCEPTION.getMsg()).put("data", errorMap);
    }
    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable){
return R.error(BizCodeEnume.UNKNOW_EXCEPTION.getCode(),BizCodeEnume.UNKNOW_EXCEPTION.getMsg());
    }
}
