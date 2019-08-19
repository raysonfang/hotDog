package cn.raysonblog.hotdog.core.exception;

import javax.servlet.http.HttpServletRequest;

import cn.raysonblog.hotdog.core.toolbox.AjaxResult;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 
 * <p>Title: GlobalExceptionHandler</p>
 * <p>Description: 全局异常类</p>
 * <p>Company: </p>
 * @author rayson
 * @date 2018-02-28 10:37
 * @version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    
   /**
     * 处理业务层异常
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public AjaxResult jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        AjaxResult ajaxResult = new AjaxResult();
        e.printStackTrace();
        ajaxResult.addError("业务受理失败:" + e.getMessage());
        return ajaxResult;
    }
    
    /**
     * 处理重复插入异常
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = DuplicateKeyException.class)
    @ResponseBody
    public AjaxResult jsonDuplicateKeyErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        AjaxResult ajaxResult = new AjaxResult();
        e.printStackTrace();
        ajaxResult.addError("业务受理失败:" + "数据已添加,请勿重复提交!");
        return ajaxResult;
    }
}
