/**
 * BusinessServiceImpl.java
 */
package com.sizheng.afl.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sizheng.afl.base.impl.BaseServiceImpl;
import com.sizheng.afl.component.ApiInvoker;
import com.sizheng.afl.component.PropUtil;
import com.sizheng.afl.component.SimpleMailSender;
import com.sizheng.afl.component.WeiXinApiInvoker;
import com.sizheng.afl.dao.IBusinessDao;
import com.sizheng.afl.pojo.constant.SysConstant;
import com.sizheng.afl.pojo.entity.Bill;
import com.sizheng.afl.pojo.entity.Business;
import com.sizheng.afl.pojo.entity.BusinessConsumer;
import com.sizheng.afl.pojo.entity.BusinessRole;
import com.sizheng.afl.pojo.entity.Qrcode;
import com.sizheng.afl.pojo.entity.Request;
import com.sizheng.afl.pojo.entity.Subscriber;
import com.sizheng.afl.pojo.entity.User;
import com.sizheng.afl.pojo.model.WeiXinBaseMsg;
import com.sizheng.afl.pojo.model.WeiXinEventType;
import com.sizheng.afl.pojo.vo.PageResult;
import com.sizheng.afl.service.IBusinessService;
import com.sizheng.afl.service.IQrcodeService;
import com.sizheng.afl.service.IRequestService;
import com.sizheng.afl.util.DateUtil;
import com.sizheng.afl.util.NumberUtil;
import com.sizheng.afl.util.StringUtil;

/**
 * 【商家】业务逻辑实现.
 * 
 * @creation 2014年03月25日 02:46:32
 * @modification 2014年03月25日 02:46:32
 * @company Skycloud
 * @author xiweicheng
 * @version 1.0
 * 
 */
@Service
@Transactional
public class BusinessServiceImpl extends BaseServiceImpl implements IBusinessService {

	private static Logger logger = Logger.getLogger(BusinessServiceImpl.class);

	@Autowired
	IBusinessDao businessDao;

	@Autowired
	ApiInvoker apiInvoker;

	@Autowired
	PropUtil propUtil;

	@Autowired
	IQrcodeService qrcodeService;

	@Autowired
	WeiXinApiInvoker weiXinApiInvoker;

	@Autowired
	SimpleMailSender simpleMailSender;

	@Autowired
	IRequestService requestService;

	@Override
	public boolean save(Locale locale, Business business) {

		logger.debug("[业务逻辑层]添加【商家】");

		business.setIsDeleted((short) 0);
		business.setQrcodeLimit(propUtil.getQrcodeBusinessMaxDefault());

		hibernateTemplate.save(business);

		return true;
	}

	@Override
	public boolean delete(Locale locale, Business business) {

		logger.debug("[业务逻辑层]删除【商家】");

		// TODO
		return true;
	}

	@Override
	public Business get(Locale locale, Business business) {

		logger.debug("[业务逻辑层]获取【商家】");

		List list = hibernateTemplate.findByExample(business);

		if (list.size() > 0) {
			com.sizheng.afl.pojo.entity.Business business3 = (com.sizheng.afl.pojo.entity.Business) list.get(0);

			return business3;
		} else {
			logger.debug("没有获取到商家信息:openid[" + business.getOpenId() + "]");
		}

		return null;
	}

	@Override
	public boolean update(Locale locale, Business business) {

		logger.debug("[业务逻辑层]更新【商家】");

		Business business2 = new Business();
		business2.setOpenId(business.getOpenId());

		List list = hibernateTemplate.findByExample(business2);

		if (list.size() > 0) {
			Business business3 = (Business) list.get(0);
			business3.setName(business.getName());
			business3.setAddress(business.getAddress());
			business3.setMail(business.getMail());
			business3.setPhoneNumber(business.getPhoneNumber());
			business3.setIntroduce(business.getIntroduce());

			hibernateTemplate.update(business3);
		} else {
			business.setQrcodeLimit(propUtil.getQrcodeBusinessMaxDefault());
			business.setIsDeleted((short) 0);

			hibernateTemplate.save(business);
		}

		return true;
	}

	@Override
	public List<Business> list(Locale locale) {

		logger.debug("[业务逻辑层]列举【商家】");

		// TODO
		return null;
	}

	@Override
	public List<Map<String, Object>> query(Locale locale, Business business) {

		logger.debug("[业务逻辑层]查询【商家】(不分页)");

		// TODO
		return null;
	}

	@Override
	public PageResult paging(Locale locale, Business business, Long start, Long limit) {

		logger.debug("[业务逻辑层]查询【商家】(分页)");

		PageResult pageResult = new PageResult();

		// TODO
		return pageResult;
	}

	@Override
	public boolean exists(Locale locale, Business business) {

		logger.debug("[业务逻辑层]判断【商家】是否存在");

		com.sizheng.afl.pojo.entity.Business business2 = new com.sizheng.afl.pojo.entity.Business();
		business2.setOpenId(business.getOpenId());

		List list = hibernateTemplate.findByExample(business2);

		return list.size() > 0;

	}

	@Override
	public long qrcodeRemain(Locale locale, String openId) {

		Business business = new Business();
		business.setOpenId(openId);

		return get(locale, business).getQrcodeLimit() - qrcodeService.queryByOpenId(locale, openId).size();
	}

	@Override
	public String addConsumer(Locale locale, WeiXinBaseMsg bean) {

		String event = bean.getEvent();
		String qrsceneId = null;
		String ticket = bean.getTicket();

		if (WeiXinEventType.SUBSCRIBE.getValue().equals(event)) {
			qrsceneId = bean.getEventKey().split(SysConstant.UNDERLINE)[1];// qrscene_123123
		} else if (WeiXinEventType.SCAN.getValue().equals(event)) {
			qrsceneId = bean.getEventKey();
		}

		Qrcode qrcode = new Qrcode();
		qrcode.setSceneId(Long.valueOf(qrsceneId));
		qrcode.setTicket(ticket);
		Qrcode qrcode2 = qrcodeService.get(locale, qrcode);

		if (qrcode2 == null) {
			logger.error(StringUtil
					.replace("该二维码没有被登记过,或者失效已被删除!二维码信息: scene id->{?1} ticket->{?2}", qrsceneId, ticket));
			return "该二维码已经失效!";
		}

		if (qrcode2.getUseTimes() >= qrcode2.getUseLimit()) {
			// 顾客实时请求记录
			Request request = new Request();
			request.setBusinessId(qrcode2.getOpenId());
			request.setConsumerId(bean.getFromUserName());
			request.setDateTime(DateUtil.now());
			request.setIsDelete(SysConstant.SHORT_FALSE);
			request.setName("二维码扫描次数受限");
			request.setSceneId(Long.valueOf(qrsceneId));
			request.setStatus(SysConstant.REQUEST_STATUS_ONGOING);
			request.setType(SysConstant.REQUEST_QRCODE_USE_LIMIT);

			requestService.save(request);

			return "该二维码已经达到扫描次数限制,请联系商家处理...";
		}

		// 查询顾客名称信息
		Subscriber subscriber = new Subscriber();
		subscriber.setUserName(bean.getFromUserName());

		List list2 = hibernateTemplate.findByExample(subscriber);

		String nickName = null;

		if (list2.size() > 0) {
			nickName = ((Subscriber) list2.get(0)).getNickname();
		} else {
			nickName = bean.getFromUserName();
		}

		// 获取商家名称信息.
		com.sizheng.afl.pojo.entity.Business business = new com.sizheng.afl.pojo.entity.Business();
		business.setOpenId(qrcode2.getOpenId());

		List list3 = hibernateTemplate.findByExample(business);

		String businessName = null;

		if (list3.size() > 0) {
			businessName = ((com.sizheng.afl.pojo.entity.Business) list3.get(0)).getName();
		}

		if (StringUtil.isEmpty(businessName)) {
			businessName = "本店";
		} else {
			businessName = businessName;
		}

		businessName = StringUtil.replace("<a href='{?1}?openId={?3}&consumerId={?4}'>{?2}</a>",
				propUtil.getRedirectUrl() + "/business/free/info.do", businessName, qrcode2.getOpenId(),
				bean.getFromUserName());

		// 在user表中记录对应的商家id
		User user = new User();
		user.setUserName(bean.getFromUserName());

		List list4 = hibernateTemplate.findByExample(user);

		// 判断是否存在对该商家的消费记录.
		BusinessConsumer businessConsumer1 = new BusinessConsumer();
		businessConsumer1.setBusinessId(qrcode2.getOpenId());
		businessConsumer1.setConsumerId(bean.getFromUserName());

		List list = hibernateTemplate.findByExample(businessConsumer1);

		if (list.size() > 0) {

			// 回头客 告知消费者&商家,消费了多少次,最后消费时间.
			BusinessConsumer businessConsumer = (BusinessConsumer) list.get(0);

			if (businessConsumer.getStatus() != null && businessConsumer.getStatus() == 1) {
				return StringUtil.replace("您还未结束消费,处于消费中...\n\n结账消费码:{?1}\n\n位置:{?2}", businessConsumer.getSceneId(),
						qrcode2.getDescription());
			}

			if (businessConsumer.getStatus() != null && businessConsumer.getStatus() == 3) {
				return StringUtil.replace("您还未结束消费,处于个人结账申请中...\n\n结账消费码:{?1}\n\n位置:{?2}",
						businessConsumer.getSceneId(), qrcode2.getDescription());
			}

			if (businessConsumer.getStatus() != null && businessConsumer.getStatus() == 4) {
				return StringUtil.replace("您还未结束消费,处于集体结账申请中...\n\n结账消费码:{?1}\n\n位置:{?2}",
						businessConsumer.getSceneId(), qrcode2.getDescription());
			}

			if (businessConsumer.getStatus() != null && businessConsumer.getStatus() == 5) {
				return StringUtil.replace("您还未被确认通过,等待商家确认,处于进入请求中...\n\n结账消费码:{?1}\n\n位置:{?2}",
						businessConsumer.getSceneId(), qrcode2.getDescription());
			}

			if (businessConsumer.getStatus() != null && businessConsumer.getStatus() == 2) {
				return "由于误操作或者系统故障,您的状态处于锁定状态,请联系商家处理...!";
			}

			if (businessConsumer.getStatus() != null && businessConsumer.getStatus() != 0) {
				return "当前状态不能扫描,请联系商家处理...!";
			}

			businessConsumer.setConsumeCode(UUID.randomUUID().toString());
			businessConsumer.setConsumeTimes(businessConsumer.getConsumeTimes() + 1);
			businessConsumer.setLastConsumeTime(DateUtil.now());
			businessConsumer.setSceneId(Long.valueOf(qrsceneId));
			businessConsumer.setStatus(SysConstant.CONSUME_STATUS_REQ);// 消费中

			hibernateTemplate.update(businessConsumer);

			if (list4.size() > 0) {
				User user2 = (User) list4.get(0);
				user2.setConsumeCode(businessConsumer.getConsumeCode());

				hibernateTemplate.update(user2);
			}

			// 顾客实时请求记录
			Request request = new Request();
			request.setBusinessId(qrcode2.getOpenId());
			request.setConsumeCode(businessConsumer.getConsumeCode());
			request.setConsumerId(bean.getFromUserName());
			request.setDateTime(DateUtil.now());
			request.setIsDelete(SysConstant.SHORT_FALSE);
			request.setName("进入请求中");
			request.setSceneId(Long.valueOf(qrsceneId));
			request.setStatus(SysConstant.REQUEST_STATUS_ONGOING);
			request.setType(SysConstant.REQUEST_TYPE_ENTER);

			requestService.save(request);

			String agreeUrl = StringUtil.replace("<a href='{?1}/business/free/joining.do?openId={?2}'>[点击此]处理请求</a>",
					propUtil.getRedirectUrl(), bean.getFromUserName());

			// 通知商家
			weiXinApiInvoker.sendServiceMsg(qrcode2.getOpenId(), StringUtil.replace(
					"顾客[{?1}]第[{?2}]次光顾!\n\n结账消费码:{?3}\n\n位置:{?4}\n\n{?5}", nickName,
					businessConsumer.getConsumeTimes(), qrsceneId, qrcode2.getDescription(), agreeUrl));

			qrcode2.setUseTimes(StringUtil.isEmpty(qrcode2.getUseTimes()) ? 1L : qrcode2.getUseTimes() + 1);
			hibernateTemplate.update(qrcode2);

			return StringUtil.replace("这是您第[{?1}]次光顾{?2}店!谢谢您的亲睐!\n\n结账消费码:{?3}\n\n请求处理中,请稍等...",
					businessConsumer.getConsumeTimes(), businessName, qrsceneId);
		} else {
			// 第一次来此商家消费 新顾客 告知消费者&商家
			BusinessConsumer businessConsumer = new BusinessConsumer();
			businessConsumer.setBusinessId(qrcode2.getOpenId());
			businessConsumer.setConsumerId(bean.getFromUserName());
			businessConsumer.setConsumeTimes(Long.valueOf(1));// 第一次记录为1
			businessConsumer.setLastConsumeTime(DateUtil.now());
			businessConsumer.setConsumeCode(UUID.randomUUID().toString());// 区别消费个人
			businessConsumer.setSceneId(Long.valueOf(qrsceneId));// 区别消费群体
			businessConsumer.setStatus(SysConstant.CONSUME_STATUS_REQ);// 接入请求中

			hibernateTemplate.save(businessConsumer);

			if (list4.size() > 0) {
				User user2 = (User) list4.get(0);
				user2.setConsumeCode(businessConsumer.getConsumeCode());

				hibernateTemplate.update(user2);
			}

			// 顾客实时请求记录
			Request request = new Request();
			request.setBusinessId(qrcode2.getOpenId());
			request.setConsumeCode(businessConsumer.getConsumeCode());
			request.setConsumerId(bean.getFromUserName());
			request.setDateTime(DateUtil.now());
			request.setIsDelete(SysConstant.SHORT_FALSE);
			request.setName("进入请求中");
			request.setSceneId(Long.valueOf(qrsceneId));
			request.setStatus(SysConstant.REQUEST_STATUS_ONGOING);
			request.setType(SysConstant.REQUEST_TYPE_ENTER);

			requestService.save(request);

			String agreeUrl = StringUtil.replace("<a href='{?1}/business/free/joining.do?openId={?2}'>[点击此]处理请求</a>",
					propUtil.getRedirectUrl(), bean.getFromUserName());

			// 通知商家
			weiXinApiInvoker.sendServiceMsg(
					qrcode2.getOpenId(),
					StringUtil.replace("顾客[{?1}]首次光顾!\n\n结账消费码:{?2}\n\n位置:{?3}\n\n{?4}", nickName, qrsceneId,
							qrcode2.getDescription(), agreeUrl));

			qrcode2.setUseTimes(StringUtil.isEmpty(qrcode2.getUseTimes()) ? 1L : qrcode2.getUseTimes() + 1);
			hibernateTemplate.update(qrcode2);

			return StringUtil.replace("这是您[首次]光顾{?1}店!谢谢您的亲睐!\n\n结账消费码:{?2}\n\n请求处理中,请稍等...", businessName, qrsceneId);
		}
	}

	@Override
	public String sendMail(Locale locale, Business business, String serverBaseUrl) {

		logger.debug("[业务逻辑层]发送登录链接到邮箱");

		String dynamicCode = NumberUtil.random(6);

		logger.debug(dynamicCode);

		Business business2 = new Business();
		business2.setOpenId(business.getOpenId());

		List list = hibernateTemplate.findByExample(business2);

		if (list.size() > 0) {
			business2 = (Business) list.get(0);
			business2.setDynamicCode(dynamicCode);

			hibernateTemplate.update(business2);
		} else {
			logger.error("商家信息不存在!");
		}

		// 发送邮件
		simpleMailSender.sendText("商家登录链接",
				StringUtil.replace("{?1}/business/login.do?openId={?2}", serverBaseUrl, business.getOpenId()),
				business.getMail());

		return dynamicCode;
	}

	@Override
	public String createDynamicCode(Locale locale, Business business) {

		logger.debug("[业务逻辑层]生成登录动态码");

		String dynamicCode = NumberUtil.random(6);

		logger.debug(dynamicCode);

		Business business2 = new Business();
		business2.setOpenId(business.getOpenId());

		List list = hibernateTemplate.findByExample(business2);

		if (list.size() > 0) {
			business2 = (Business) list.get(0);
			business2.setDynamicCode(dynamicCode);

			hibernateTemplate.update(business2);
		} else {
			logger.error("商家信息不存在!");
		}

		return dynamicCode;
	}

	@Override
	public List<Map<String, Object>> listCustomer(Locale locale, Business business, String status) {

		return businessDao.listCustomer(locale, business, status);
	}

	@Override
	public List<Map<String, Object>> queryGroupInfo(Locale locale, BusinessConsumer businessConsumer) {

		return businessDao.queryGroupInfo(locale, businessConsumer);
	}

	@Override
	public double getConsume(Locale locale, String consumeCode) {
		return businessDao.getConsume(locale, consumeCode);
	}

	@Override
	public double getTotalConsume(Locale locale, String sceneId) {
		return businessDao.getTotalConsume(locale, sceneId);
	}

	@Override
	public double getConsume(Locale locale, String consumeCode, String ownOrGroup) {

		if ("own".equals(ownOrGroup)) {
			return getConsume(locale, consumeCode);
		} else if ("group".equals(ownOrGroup)) {
			BusinessConsumer businessConsumer = new BusinessConsumer();
			businessConsumer.setConsumeCode(consumeCode);

			List list = hibernateTemplate.findByExample(businessConsumer);

			if (list.size() > 0) {
				return getTotalConsume(locale, ((BusinessConsumer) list.get(0)).getSceneId().toString());
			} else {
				logger.error("不存在消费信息!");
			}
		} else {
			logger.error("不识别结账类型;ownOrGroup:" + ownOrGroup);
		}

		return 0;
	}

	@Override
	public Boolean checkout(Locale locale, BusinessConsumer businessConsumer) {

		if (businessConsumer.getStatus() == 3) {
			BusinessConsumer businessConsumer2 = new BusinessConsumer();
			businessConsumer2.setConsumeCode(businessConsumer.getConsumeCode());
			businessConsumer2.setConsumerId(businessConsumer.getConsumerId());
			businessConsumer2.setSceneId(businessConsumer.getSceneId());

			List list = hibernateTemplate.findByExample(businessConsumer2);

			if (list.size() > 0) {
				double amount = getConsume(locale, businessConsumer.getConsumeCode(), "own");

				BusinessConsumer businessConsumer3 = (BusinessConsumer) list.get(0);

				businessConsumer3.setStatus((short) 0);
				businessConsumer3.setConsumeCode(null);
				businessConsumer3.setSceneId(null);

				hibernateTemplate.update(businessConsumer3);

				Bill bill = new Bill();
				bill.setAmount(BigDecimal.valueOf(amount));
				bill.setBusinessId(businessConsumer3.getBusinessId());
				bill.setConsumeCode(businessConsumer.getConsumeCode());
				bill.setConsumerId(businessConsumer.getConsumerId());
				bill.setDateTime(DateUtil.now());
				bill.setSceneId(businessConsumer.getSceneId());
				bill.setType((short) 0);

				hibernateTemplate.save(bill);

				User user = new User();
				user.setUserName(businessConsumer.getConsumerId());

				List list2 = hibernateTemplate.findByExample(user);

				if (list2.size() > 0) {
					User user2 = (User) list2.get(0);
					user2.setConsumeCode(null);

					hibernateTemplate.update(user2);
				}

				return true;
			} else {
				logger.error("消费信息不存在!");
			}
		} else if (businessConsumer.getStatus() == 4) {
			BusinessConsumer businessConsumer2 = new BusinessConsumer();
			businessConsumer2.setSceneId(businessConsumer.getSceneId());

			List list = hibernateTemplate.findByExample(businessConsumer2);

			if (list.size() > 0) {

				String businessId = null;
				double amount = getConsume(locale, businessConsumer.getConsumeCode(), "group");

				for (Object object : list) {
					BusinessConsumer businessConsumer3 = (BusinessConsumer) object;
					businessId = businessConsumer3.getBusinessId();

					if (businessConsumer3.getStatus() == 1 || businessConsumer3.getStatus() == 3
							|| businessConsumer3.getStatus() == 4) {

						businessConsumer3.setStatus((short) 0);
						businessConsumer3.setConsumeCode(null);
						businessConsumer3.setSceneId(null);

						hibernateTemplate.update(businessConsumer3);

						User user = new User();
						user.setUserName(businessConsumer3.getConsumerId());

						List list2 = hibernateTemplate.findByExample(user);

						if (list2.size() > 0) {
							User user2 = (User) list2.get(0);
							user2.setConsumeCode(null);

							hibernateTemplate.update(user2);
						}
					}
				}

				Bill bill = new Bill();
				bill.setAmount(BigDecimal.valueOf(amount));
				bill.setBusinessId(businessId);
				bill.setConsumeCode(businessConsumer.getConsumeCode());
				bill.setConsumerId(businessConsumer.getConsumerId());
				bill.setDateTime(DateUtil.now());
				bill.setSceneId(businessConsumer.getSceneId());
				bill.setType((short) 1);

				hibernateTemplate.save(bill);

				return true;

			} else {
				logger.error("消费信息不存在!");
			}
		}

		return false;
	}

	@Override
	public long getGroupSize(Locale locale, String consumeCode) {
		return businessDao.getGroupSize(locale, consumeCode);
	}

	@Override
	public Map<String, Object> getConsumer(Locale locale, String openId) {
		return businessDao.getConsumer(locale, openId);
	}

	@Override
	public Boolean agreeOrDisagree(Locale locale, String consumeCode, boolean agree) {

		BusinessConsumer businessConsumer = new BusinessConsumer();
		businessConsumer.setConsumeCode(consumeCode);

		List list = hibernateTemplate.findByExample(businessConsumer);

		if (list.size() > 0) {
			BusinessConsumer businessConsumer2 = (BusinessConsumer) list.get(0);
			businessConsumer2
					.setStatus(agree ? SysConstant.CONSUME_STATUS_ONGOING : SysConstant.CONSUME_STATUS_DISABLE);

			if (!agree) {
				businessConsumer2.setConsumeCode(null);
				businessConsumer2.setSceneId(null);
			}

			hibernateTemplate.update(businessConsumer2);

			if (!agree) {

				User user = new User();
				user.setUserName(businessConsumer2.getConsumerId());

				List list2 = hibernateTemplate.findByExample(user);

				if (list2.size() > 0) {
					User user2 = (User) list2.get(0);
					user2.setConsumeCode(null);

					hibernateTemplate.update(user2);
				} else {
					logger.error("用户信息不存在!");
				}
			}

			return true;
		} else {
			logger.error("消费信息不存在!");
			return false;
		}
	}

	@Override
	public Boolean enableConsumer(Locale locale, String consumerId, String businessId) {

		BusinessConsumer businessConsumer = new BusinessConsumer();
		businessConsumer.setConsumerId(consumerId);
		businessConsumer.setBusinessId(businessId);

		List list = hibernateTemplate.findByExample(businessConsumer);

		if (list.size() > 0) {
			BusinessConsumer businessConsumer2 = (BusinessConsumer) list.get(0);
			businessConsumer2.setStatus(SysConstant.CONSUME_STATUS_STOP);

			hibernateTemplate.update(businessConsumer2);

			return true;
		} else {
			logger.error("消费信息不存在!");
			return false;
		}
	}

	@Override
	public List<Map<String, Object>> queryRequest(Locale locale, String openId) {
		return businessDao.queryRequest(locale, openId);
	}

	@Override
	public String addRole(Locale locale, WeiXinBaseMsg bean) {

		String event = bean.getEvent();
		String qrsceneId = null;
		String ticket = bean.getTicket();

		if (WeiXinEventType.SUBSCRIBE.getValue().equals(event)) {
			qrsceneId = bean.getEventKey().split(SysConstant.UNDERLINE)[1];// qrscene_123123
		} else if (WeiXinEventType.SCAN.getValue().equals(event)) {
			qrsceneId = bean.getEventKey();
		}

		Qrcode qrcode = new Qrcode();
		qrcode.setSceneId(Long.valueOf(qrsceneId));
		qrcode.setTicket(ticket);
		Qrcode qrcode2 = qrcodeService.get(locale, qrcode);

		if (qrcode2 == null) {
			logger.error(StringUtil
					.replace("该二维码没有被登记过,或者失效已被删除!二维码信息: scene id->{?1} ticket->{?2}", qrsceneId, ticket));
			return "该二维码已经失效!";
		}

		BusinessRole businessRole = new BusinessRole();
		businessRole.setBusinessId(qrcode2.getOpenId());
		businessRole.setDateTime(DateUtil.now());
		businessRole.setIsDelete(SysConstant.SHORT_FALSE);
		businessRole.setOpenId(bean.getFromUserName());
		businessRole.setType(SysConstant.ROLE_TYPE_UNDETERMINED);

		hibernateTemplate.save(businessRole);

		return "扫描添加成功,等待角色分配中...";
	}

	@Override
	public List<Map<String, Object>> listMgrRoles(Locale locale, BusinessRole businessRole) {
		return businessDao.listMgrRoles(locale, businessRole);
	}

	@Override
	public boolean setRole(Locale locale, final BusinessRole businessRole) {

		return hibernateTemplate.execute(new HibernateCallback<Boolean>() {

			@Override
			public Boolean doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createQuery("update BusinessRole set type=? where id=?")
						.setShort(0, businessRole.getType()).setLong(1, businessRole.getId()).executeUpdate() == 1;
			}
		});
	}

	@Override
	public boolean deleteRole(Locale locale, final BusinessRole businessRole) {

		return hibernateTemplate.execute(new HibernateCallback<Boolean>() {

			@Override
			public Boolean doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createQuery("update BusinessRole set isDelete=? where id=?").setShort(0, (short) 1)
						.setLong(1, businessRole.getId()).executeUpdate() == 1;
			}

		});
	}

}