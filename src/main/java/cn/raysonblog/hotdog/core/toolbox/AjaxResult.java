package cn.raysonblog.hotdog.core.toolbox;

import java.io.Serializable;
import java.util.HashMap;

import com.alibaba.fastjson.JSON;

/**
 * 
 * <p>Title: AjaxResult</p>
 * <p>Description: ajax结果返回类</p>
 * <p>Company: </p>
 * @author rayson
 * @date 2018-02-28 10:38
 * @version 1.0
 */
public class AjaxResult implements Serializable {
		/**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

        // 返回状态码   (默认10000:成功,其它:失败)
		private String code = ResultCode.SUCCESS.getCode();
		
		// 返回的中文消息
		private String message;
		
		/*private boolean success;
		
		public void setSuccess(boolean success) {
			this.success = success;
		}*/

		// 成功时携带的数据
		private Object data;
		
		public String getCode() {
			return code;
		}
		
		public AjaxResult setCode(String code) {
			this.code = code;
			return this;
		}
		
		public String getMessage() {
			return message;
		}
		
		public AjaxResult setMessage(String message) {
			this.message = message;
			return this;
		}
		
		public Object getData() {
			return data;
		}
		
		public AjaxResult setData(Object data) {
			this.data = data;
			return this;
		}
		
		public AjaxResult addSuccess(String message) {
			this.message = message;
			this.code = ResultCode.SUCCESS.getCode();
			this.data = null;
			return this;
		}
		
		public AjaxResult addError(String message) {
			this.message = message;
			this.code = ResultCode.ERROR.getCode();
			this.data = null;
			return this;
		}
		
		public AjaxResult addParamError(String message) {
            this.message = message;
            this.code = ResultCode.PARAMERROR.getCode();
            this.data = null;
            return this;
        }
		
		
		public AjaxResult addFail(String message) {
			this.message = message;
			this.code = ResultCode.FAILED.getCode();
			this.data = null;
			return this;
		}
		
		/*public AjaxResult addWarn(String message) {
			this.message = message;
			this.code = 333;
			this.data = null;
			return this;
		}*/
		
		public static AjaxResult success(Object data) {
			AjaxResult ajaxResult = new AjaxResult();
			ajaxResult.message = ResultCode.SUCCESS.getMsg();
			ajaxResult.data = data;
			ajaxResult.code = ResultCode.SUCCESS.getCode();
			return ajaxResult;
		}
		
		public boolean isSuccess() {
			return ResultCode.SUCCESS.getCode().equals(getCode());
		}
		
		@Override
		public String toString() {
			return JSON.toJSONString(this);
		}
}
