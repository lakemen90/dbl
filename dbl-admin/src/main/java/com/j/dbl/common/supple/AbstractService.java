package com.j.dbl.common.supple;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.page.PageMethod;
import com.j.dbl.pojo.AjaxResult;
import com.j.dbl.pojo.JqPage;
import com.j.dbl.pojo.PageAjax;
import com.j.dbl.pojo.ParamData;
import com.j.dbl.common.redis.RedisDao;
import com.j.dbl.common.util.AppUtil;
import com.j.dbl.common.util.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author chenjp
 * @create 2018-09-16 1:27
 * @desc 通用接口
 * @param <T>
 */
@Service
public abstract class AbstractService<T> {
    
    @Autowired
    protected RedisDao redisDao;
    @Autowired
    protected DaoSupport dao;

    /**
     * 通用查询对象AjaxResult返回方式
     * @param str
     * @param args
     * @return
     */
    public AjaxResult findForObject(String str, String[] args) {
     	ParamData params = new ParamData();
    	//校验参数
    	String result = AppUtil.checkParam(params, args);
    	Object data = null;
    	if(StringUtils.isEmpty(result)){
    		data = dao.findForObject(str, params);
    	}
    	return AppUtil.returnObj(result, data);
    }


    /**
     * 通用查询列表
     * @param str
     * @param args
     * @return
     */
    public AjaxResult findForList(String str, String[] args) {
    	ParamData params = new ParamData();
    	//校验参数
    	String result = AppUtil.checkParam(params, args);
    	List<?> list = null;
    	if(StringUtils.isEmpty(result)){
    		list = dao.findForList(str, params);
    	}
    	return AppUtil.returnList(result, list);
    }
    
    /**
     * 通用查询分页PageAjax方式
     * @param str
     * @param params
     * @return
     */
    public PageAjax<Map<String,Object>> pageQuery(String str,ParamData params){
        startPage(params);
        List<Map<String,Object>> dataList =(List<Map<String,Object>>)dao.findForList(str, params);
        return new PageAjax<Map<String,Object>>(dataList);
    }

	/**
	 * 通用查询分页JqPage方式
	 * @param str
	 * @param params
	 * @return
	 */
	public JqPage<Map<String,Object>> jqPageQuery(String str,ParamData params){
		startPage(params);
		List<Map<String,Object>> dataList =(List<Map<String,Object>>)dao.findForList(str, params);
		return new JqPage<Map<String,Object>>(dataList);
	}
    
    /**
     * 通用查询分页
     * @param str
     * @param args
     * @return
     */
    public AjaxResult findForPage(String str, String[] args) {
    	ParamData params = new ParamData();
    	//校验参数
    	String result = AppUtil.checkParam(params, args);
    	List<?> list = null;
    	if(StringUtils.isEmpty(result)){
    		startPage(params);
    		list = dao.findForList(str, params);
    	}
    	return AppUtil.returnPage(result, list);
    }
    
    /**
     * 查询指定字段的值
     */
    public Map<?, ?> findForMap(String str, String key) {
    	return dao.findForMap(str, new ParamData(), key);
    }
    
    /**
     * 通用保存
     * @param str
     * @param args
     * @return
     */
    public AjaxResult save(String str, String[] args) {
    	ParamData params = new ParamData();
    	//校验参数
    	String result = AppUtil.checkParam(params, args);
    	if(StringUtils.isEmpty(result)){
    		int ret = saveInfo(str, params);
    		if(ret < 1){
    			result = "添加失败";
    		}
    	}
		return AppUtil.returnObj(result);
    }
    
    public int saveInfo(String str, ParamData params){
    	return dao.save(str, params);
    }
    
    /**
     * 通用批量保存
     * @param str
     * @param list
     * @return
     */
    public AjaxResult batchSave(String str, List<?> list) {
    	String result = "添加失败";
    	if(CollectionUtils.isNotEmpty(list)){
        	int ret = saveList(str, list);
        	if(ret > 0){
        		result = null;
        	}
    	}
    	return AppUtil.returnObj(result);
    }
    
    public int saveList(String str, List<?> list){
    	return dao.batchSave(str, list);
    }
    
    /**
     * 通用修改
     * @param str
     * @param args
     * @return
     */
    public AjaxResult update(String str, String[] args) {
    	ParamData params = new ParamData();
    	//校验参数
    	String result = AppUtil.checkParam(params, args);
    	if(StringUtils.isEmpty(result)){
    		int ret = updateBy(str, params);
    		if(ret < 1){
    			result = "修改失败";
    		}
    	}
		return AppUtil.returnObj(result);
    }
    
    public int updateBy(String str, ParamData params){
    	return dao.update(str, params);
    }
    
    /**
     * 通用批量修改
     * @param str
     * @param list
     * @return
     */
    public AjaxResult batchUpdate(String str, List<?> list) {
    	String result = "修改失败";
    	if(CollectionUtils.isNotEmpty(list)){
        	int ret = batUpdate(str, list);
        	if(ret > 0){
        		result = null;
        	}
    	}
    	return AppUtil.returnObj(result);
    }
    
    public int batUpdate(String str, List<?> list){
    	return dao.batchUpdate(str, list);
    }
    
    /**
     * 通用删除
     * @param str
     * @param args
     * @return
     */
    public AjaxResult delete(String str, String[] args) {
    	ParamData params = new ParamData();
    	//校验参数
    	String result = AppUtil.checkParam(params, args);
    	if(StringUtils.isEmpty(result)){
    		int ret = delBy(str, params);
    		if(ret < 1){
    			result = "删除失败";
    		}
    	}
    	return AppUtil.returnObj(result);
    }
    
    public int delBy(String str, ParamData params){
    	return dao.delete(str, params);
    }
    
    /**
     * 通用批量删除
     * @param str
     * @param list
     * @return
     */
    public AjaxResult batchDelete(String str, int[] ids) {
    	String result = "删除失败";
    	if(null != ids && ids.length > 0){
        	int ret = batchDel(str, ids);
        	if(ret > 0){
        		result = null;
        	}
    	}
    	return AppUtil.returnObj(result);
    }
    
    public int batchDel(String str, int[] ids){
    	return dao.batchDelete(str, ids);
    }

	/**
	 * 开始分页
	 * @param params
	 */
	public static void startPage(ParamData params) {
		int pageNo = 1;
		int pageSize = 10;
		if (null != params.get("pageNo") && null != params.get("pageSize")) {
			pageNo = params.getInt("pageNo");
			pageSize = params.getInt("pageSize");
		}
		PageMethod.startPage(pageNo, pageSize);
	}

    @Autowired
    protected Mapper<T> mapper;

    public Mapper<T> getMapper() {
        return mapper;
    }

    public T queryByID(Object key) {
        return mapper.selectByPrimaryKey(key);
    }
    
    public List<T> queryList(T entity){
    	return mapper.select(entity);
    }
    
    public T queryOne(T entity){
    	return mapper.selectOne(entity);
    }
    
    public int queryCount(T entity){
    	return mapper.selectCount(entity);
    }

    public List<T> queryByExample(Example example) {
        return mapper.selectByExample(example);
    }
    
    public List<T> queryAll(){
    	return mapper.selectAll();
    }

    public PageAjax<T> queryPage(PageAjax<T> page, T entity){
    	PageMethod.startPage(page.getPageNo(), page.getPageSize());
    	List<T> list = queryList(entity);
        return new PageAjax<T>(list);
    }

	public JqPage<T> queryPage(JqPage<T> page, T entity){
		PageMethod.startPage(page.getCurrPage(), page.getPageSize());
		List<T> list = queryList(entity);
		return new JqPage<T>(list);
	}

    public AjaxResult save(T entity) {
        int ret = insert(entity);
        String result = null;
        if(ret <= 0){
        	result = "添加失败";
        }
    	return AppUtil.returnObj(result);
    }
	
	public int insert(T entity){
		return mapper.insert(entity);
	}

    public AjaxResult saveNotNull(T entity) {
    	int ret = insertSelective(entity);
        String result = null;
        if(ret <= 0){
        	result = "更新失败";
        }
    	return AppUtil.returnObj(result);
    }
	
	public int insertSelective(T entity){
		return mapper.insertSelective(entity);
	}

    public AjaxResult deleteByID(Object key) {
        int ret = delById(key);
        String result = null;
        if(ret <= 0){
        	result = "删除失败";
        }
    	return AppUtil.returnObj(result);
    }
	
	public int delById(Object key){
		return mapper.deleteByPrimaryKey(key);
	}

    public AjaxResult update(T entity) {
    	int ret = updateByID(entity);
        String result = null;
        if(ret <= 0){
        	result = "更新失败";
        }
    	return AppUtil.returnObj(result);
    }
	
	/**
	 * 根据主键更新属性不为null的值
	 * @param entity
	 * @return
	 */
	public int updateByID(T entity){
		return mapper.updateByPrimaryKeySelective(entity);
	}
	

	public ParamData getParamData(Integer pageNo,Integer pageSize){
	    ParamData params = new ParamData();
	    if(!StringUtil.isEmpty(pageNo))
	        params.put("pageNo",pageNo);
	    if(!StringUtil.isEmpty(pageSize))    
	        params.put("pageSize",pageSize);
	    return params;
	}

	protected static HttpServletRequest getRequest(){
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	/**
	 * 分页查询
	 * @param page
	 * @param rows
	 * @param example
	 * @return
	 */
	public PageAjax<T> queryPageListByExample(Integer page, Integer rows, Example example) {
		// 设置分页条件
		PageHelper.startPage(page, rows);
		List<T> list = this.queryByExample(example);
		return new PageAjax<T>(list);
	}

	/**
	 * 分页查询
	 * @param page
	 * @param rows
	 * @param example
	 * @return
	 */
	public JqPage<T> queryJqPageListByExample(Integer page, Integer rows, Example example) {
		// 设置分页条件
		PageHelper.startPage(page, rows);
		List<T> list = this.queryByExample(example);
		return new JqPage<T>(list);
	}
//
//	/**
//	 * 获取当前登陆用户(后台)
//	 */
//	public AuthUser getLoginUser(){
//		return (AuthUser) ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).
//				getRequest().getAttribute("loginUser");
//	}
//
//	public Integer getLoginUserComId(){
//		return ((AuthUser) ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).
//				getRequest().getAttribute("loginUser")).getComId();
//	}
//
//	/**
//	 * 获取当前登陆用户(接口)
//	 * @return
//	 */
//	public Member getMember(String token){
//		Member member=(Member)this.redisDao.getObject(token);
//		if(member==null || StringUtil.isEmpty(member.getMemberId()))
//			throw new AppLoginException("请先登录....");
//		return member;
//	}
//
//	public Long getMemberId(String token){
//		Member member=(Member)this.redisDao.getObject(token);
//		if(member==null || StringUtil.isEmpty(member.getMemberId()))
//			throw new AppLoginException("请先登录....");
//		return member.getMemberId();
//	}

}
