package com.sizheng.afl.pojo.entity;

// Generated 2014-3-30 15:40:35 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * BusinessConsumer generated by hbm2java
 */
@Entity
@Table(name = "business_consumer", catalog = "afl")
public class BusinessConsumer implements java.io.Serializable {

	private Long id;
	private String businessId;
	private String consumerId;
	private Long consumeTimes;
	private Date lastConsumeTime;
	private Long sceneId;
	private String consumeCode;
	private Short status;

	public BusinessConsumer() {
	}

	public BusinessConsumer(String businessId, String consumerId, Long consumeTimes, Date lastConsumeTime,
			Long sceneId, String consumeCode, Short status) {
		this.businessId = businessId;
		this.consumerId = consumerId;
		this.consumeTimes = consumeTimes;
		this.lastConsumeTime = lastConsumeTime;
		this.sceneId = sceneId;
		this.consumeCode = consumeCode;
		this.status = status;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "business_id")
	public String getBusinessId() {
		return this.businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	@Column(name = "consumer_id")
	public String getConsumerId() {
		return this.consumerId;
	}

	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}

	@Column(name = "consume_times")
	public Long getConsumeTimes() {
		return this.consumeTimes;
	}

	public void setConsumeTimes(Long consumeTimes) {
		this.consumeTimes = consumeTimes;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_consume_time", length = 19)
	public Date getLastConsumeTime() {
		return this.lastConsumeTime;
	}

	public void setLastConsumeTime(Date lastConsumeTime) {
		this.lastConsumeTime = lastConsumeTime;
	}

	@Column(name = "scene_id")
	public Long getSceneId() {
		return this.sceneId;
	}

	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}

	@Column(name = "consume_code", length = 500)
	public String getConsumeCode() {
		return this.consumeCode;
	}

	public void setConsumeCode(String consumeCode) {
		this.consumeCode = consumeCode;
	}

	@Column(name = "status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

}