package cn.raysonblog.hotdog.base.entity;

import lombok.Data;

/**
 * 
 * @desctript  存放前端分页/查询/排序参数
 * @author toothword 
 * @param <E> 持久层实体对象,用于保存查询参数
 */
@Data
public class PagingandEntity<E extends BaseModel<E>> {
	/**
	 * 排序字段
	 */
	private String sort;
    /**
     *排序方式:asc or desc
     */
    private String order;

    /**
     * 第几页
     */
    private Integer page=1;
    
    /**
     * 每页条数
     */
    private Integer pageCount=100;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 需要查询的介于开始与结束之间的字段
     */
    private String between;
    /**
     * 跨应用请求token,后台需要跨应用调取接口的时候需要提供
     */
    private String token;
    /**
     * equals查询实体
     */
    private E eq;
    /**
     * not equals查询实体
     */
    private E ne;
    /**
     * like查询实体
     */
    private E like;
    /**
     * notlike查询实体
     */
    private E notlike;
    /**
     * in查询实体(属性值为多个值得拼接，以逗号分隔)
     */
    private E in;
    private static final long serialVersionUID = 1L;
}