package com.sizheng.afl.pojo.entity;

// Generated 2014-4-23 18:00:43 by Hibernate Tools 3.4.0.CR1

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
 * Request generated by hbm2java
 */
@Entity
@Table(name = "request", catalog = "afl")
public class Request implements java.io.Serializable {

	private Long id;
	private String name;
	private String consumerId;
	private String businessId;
	private String consumeCode;
	private Long sceneId;
	private Short type;
	private Short status;
	private Date dateTime;
	private Short isDelete;

	public Request() {
	}

	public Request(String name, String consumerId, String businessId, String consumeCode, Long sceneId, Short type,
			Short status, Date dateTime, Short isDelete) {
		this.name = name;
		this.consumerId = consumerId;
		this.businessId = businessId;
		this.consumeCode = consumeCode;
		this.sceneId = sceneId;
		this.type = type;
		this.status = status;
		this.dateTime = dateTime;
		this.isDelete = isDelete;
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

	@Column(name = "name", length = 1000)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "consumer_id")
	public String getConsumerId() {
		return this.consumerId;
	}

	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}

	@Column(name = "business_id")
	public String getBusinessId() {
		return this.businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	@Column(name = "consume_code", length = 500)
	public String getConsumeCode() {
		return this.consumeCode;
	}

	public void setConsumeCode(String consumeCode) {
		this.consumeCode = consumeCode;
	}

	@Column(name = "scene_id")
	public Long getSceneId() {
		return this.sceneId;
	}

	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}

	@Column(name = "type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@Column(name = "status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_time", length = 19)
	public Date getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	@Column(name = "is_delete")
	public Short getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}

}
