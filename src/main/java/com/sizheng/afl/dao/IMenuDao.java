/**
 * IMenuDao.java
 */
package com.sizheng.afl.dao;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.sizheng.afl.pojo.entity.Menu;

/**
 * 【菜单】持久化接口层.
 * 
 * @creation 2014年03月29日 08:37:32
 * @modification 2014年03月29日 08:37:32
 * @company Skycloud
 * @author xiweicheng
 * @version 1.0
 * 
 */
public interface IMenuDao {

	/**
	 * 查询【菜单】.
	 * 
	 * @author xiweicheng
	 * @creation 2014年03月29日 08:37:32
	 * @modification 2014年03月29日 08:37:32
	 * @param locale
	 * @param menu
	 * @param consumeCode TODO
	 * @param start
	 * @param limit
	 * @param order TODO
	 * @param consumerId TODO
	 * @return
	 */
	List<Map<String, Object>> query(Locale locale, Menu menu, String consumeCode, Long start, Long limit, String order, String consumerId);

	/**
	 * 查询总数查询【菜单】.
	 * 
	 * @author xiweicheng
	 * @creation 2014年03月29日 08:37:32
	 * @modification 2014年03月29日 08:37:32
	 * @param locale
	 * @param menu
	 * @return
	 */
	long queryCount(Locale locale, Menu menu);

	/**
	 * 商家菜单列表查看.
	 * 
	 * @author xiweicheng
	 * @creation 2014年4月6日 下午7:06:42
	 * @modification 2014年4月6日 下午7:06:42
	 * @param locale
	 * @param menu
	 * @param start
	 * @param limit
	 * @return
	 */
	List<Map<String, Object>> queryBillList(Locale locale, Menu menu, Long start, Long limit);

	/**
	 * 订单查询.
	 * 
	 * @author xiweicheng
	 * @creation 2014年4月13日 下午8:03:35
	 * @modification 2014年4月13日 下午8:03:35
	 * @param locale
	 * @param menu
	 * @return
	 */
	List<Map<String, Object>> queryOrderMapList(Locale locale, Menu menu);

	/**
	 * 
	 * 
	 * @author xiweicheng
	 * @creation 2014年4月13日 下午10:22:16
	 * @modification 2014年4月13日 下午10:22:16
	 * @param locale
	 * @param menu
	 * @return
	 */
	List<Map<String, Object>> queryJoinBill(Locale locale, Menu menu);

	boolean billSubmit(Locale locale, String openId);
	
}
