package com.sizheng.afl.pojo.entity;

// Generated 2014-4-4 9:54:03 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Qrcode generated by hbm2java
 */
@Entity
@Table(name = "qrcode", catalog = "afl")
public class Qrcode implements java.io.Serializable {

	private Long id;
	private String openId;
	private Long sceneId;
	private Long useLimit;
	private Long useTimes;
	private Long categoryId;
	private String ticket;
	private String url;
	private String filePath;
	private String type;
	private String myUrl;
	private String description;

	public Qrcode() {
	}

	public Qrcode(String openId, Long sceneId, Long useLimit, Long useTimes, Long categoryId, String ticket,
			String url, String filePath, String type, String myUrl, String description) {
		this.openId = openId;
		this.sceneId = sceneId;
		this.useLimit = useLimit;
		this.useTimes = useTimes;
		this.categoryId = categoryId;
		this.ticket = ticket;
		this.url = url;
		this.filePath = filePath;
		this.type = type;
		this.myUrl = myUrl;
		this.description = description;
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

	@Column(name = "open_id")
	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Column(name = "scene_id")
	public Long getSceneId() {
		return this.sceneId;
	}

	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}

	@Column(name = "use_limit")
	public Long getUseLimit() {
		return this.useLimit;
	}

	public void setUseLimit(Long useLimit) {
		this.useLimit = useLimit;
	}

	@Column(name = "use_times")
	public Long getUseTimes() {
		return this.useTimes;
	}

	public void setUseTimes(Long useTimes) {
		this.useTimes = useTimes;
	}

	@Column(name = "category_id")
	public Long getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "ticket", length = 1000)
	public String getTicket() {
		return this.ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	@Column(name = "url", length = 1000)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "filePath", length = 1000)
	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Column(name = "type", length = 50)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "my_url", length = 1000)
	public String getMyUrl() {
		return this.myUrl;
	}

	public void setMyUrl(String myUrl) {
		this.myUrl = myUrl;
	}

	@Column(name = "description", length = 1000)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
