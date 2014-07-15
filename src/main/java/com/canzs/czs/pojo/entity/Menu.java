package com.canzs.czs.pojo.entity;

// Generated 2014-4-30 11:35:37 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
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
 * Menu generated by hbm2java
 */
@Entity
@Table(name = "menu", catalog = "czs")
public class Menu implements java.io.Serializable {

	private Long id;
	private String name;
	private Long categoryId;
	private BigDecimal price;
	private BigDecimal privilege;
	private Long tasteId;
	private String introduce;
	private Long resourceId;
	private Short isDelete;
	private String owner;
	private Date dateTime;
	private Long orderTimes;
	private String tags;

	public Menu() {
	}

	public Menu(String name, Long categoryId, BigDecimal price, BigDecimal privilege, Long tasteId, String introduce,
			Long resourceId, Short isDelete, String owner, Date dateTime, Long orderTimes, String tags) {
		this.name = name;
		this.categoryId = categoryId;
		this.price = price;
		this.privilege = privilege;
		this.tasteId = tasteId;
		this.introduce = introduce;
		this.resourceId = resourceId;
		this.isDelete = isDelete;
		this.owner = owner;
		this.dateTime = dateTime;
		this.orderTimes = orderTimes;
		this.tags = tags;
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

	@Column(name = "name", length = 500)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "category_id")
	public Long getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "price", precision = 10)
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name = "privilege", precision = 10)
	public BigDecimal getPrivilege() {
		return this.privilege;
	}

	public void setPrivilege(BigDecimal privilege) {
		this.privilege = privilege;
	}

	@Column(name = "taste_id")
	public Long getTasteId() {
		return this.tasteId;
	}

	public void setTasteId(Long tasteId) {
		this.tasteId = tasteId;
	}

	@Column(name = "introduce", length = 2000)
	public String getIntroduce() {
		return this.introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	@Column(name = "resource_id")
	public Long getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	@Column(name = "is_delete")
	public Short getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}

	@Column(name = "owner")
	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_time", length = 19)
	public Date getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	@Column(name = "order_times")
	public Long getOrderTimes() {
		return this.orderTimes;
	}

	public void setOrderTimes(Long orderTimes) {
		this.orderTimes = orderTimes;
	}

	@Column(name = "tags")
	public String getTags() {
		return this.tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

}