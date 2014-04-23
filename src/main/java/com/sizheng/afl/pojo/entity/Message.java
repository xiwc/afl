package com.sizheng.afl.pojo.entity;

// Generated 2014-4-23 17:05:01 by Hibernate Tools 3.4.0.CR1

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
 * Message generated by hbm2java
 */
@Entity
@Table(name = "message", catalog = "afl")
public class Message implements java.io.Serializable {

	private Integer id;
	private String fromUserName;
	private Long createTime;
	private String msgType;
	private String content;
	private Long msgId;
	private String picUrl;
	private String mediaId;
	private String format;
	private String thumbMediaId;
	private BigDecimal locationX;
	private BigDecimal locationY;
	private BigDecimal scale;
	private String label;
	private String title;
	private String description;
	private String url;
	private String eventKey;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private BigDecimal precision;
	private String ticket;
	private String event;
	private String toOpenId;
	private Date dateTime;
	private Short status;
	private Short isDelete;

	public Message() {
	}

	public Message(String fromUserName, Long createTime, String msgType, String content, Long msgId, String picUrl,
			String mediaId, String format, String thumbMediaId, BigDecimal locationX, BigDecimal locationY,
			BigDecimal scale, String label, String title, String description, String url, String eventKey,
			BigDecimal latitude, BigDecimal longitude, BigDecimal precision, String ticket, String event,
			String toOpenId, Date dateTime, Short status, Short isDelete) {
		this.fromUserName = fromUserName;
		this.createTime = createTime;
		this.msgType = msgType;
		this.content = content;
		this.msgId = msgId;
		this.picUrl = picUrl;
		this.mediaId = mediaId;
		this.format = format;
		this.thumbMediaId = thumbMediaId;
		this.locationX = locationX;
		this.locationY = locationY;
		this.scale = scale;
		this.label = label;
		this.title = title;
		this.description = description;
		this.url = url;
		this.eventKey = eventKey;
		this.latitude = latitude;
		this.longitude = longitude;
		this.precision = precision;
		this.ticket = ticket;
		this.event = event;
		this.toOpenId = toOpenId;
		this.dateTime = dateTime;
		this.status = status;
		this.isDelete = isDelete;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "from_user_name")
	public String getFromUserName() {
		return this.fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	@Column(name = "create_time")
	public Long getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@Column(name = "msg_type")
	public String getMsgType() {
		return this.msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "msg_id")
	public Long getMsgId() {
		return this.msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	@Column(name = "pic_url", length = 500)
	public String getPicUrl() {
		return this.picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	@Column(name = "media_id")
	public String getMediaId() {
		return this.mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	@Column(name = "format")
	public String getFormat() {
		return this.format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	@Column(name = "thumb_media_id")
	public String getThumbMediaId() {
		return this.thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	@Column(name = "location_x", precision = 10, scale = 6)
	public BigDecimal getLocationX() {
		return this.locationX;
	}

	public void setLocationX(BigDecimal locationX) {
		this.locationX = locationX;
	}

	@Column(name = "location_y", precision = 10, scale = 6)
	public BigDecimal getLocationY() {
		return this.locationY;
	}

	public void setLocationY(BigDecimal locationY) {
		this.locationY = locationY;
	}

	@Column(name = "scale", precision = 10, scale = 6)
	public BigDecimal getScale() {
		return this.scale;
	}

	public void setScale(BigDecimal scale) {
		this.scale = scale;
	}

	@Column(name = "label", length = 1000)
	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Column(name = "title", length = 500)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "description", length = 1000)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "url", length = 500)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "event_key", length = 500)
	public String getEventKey() {
		return this.eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	@Column(name = "latitude", precision = 10, scale = 6)
	public BigDecimal getLatitude() {
		return this.latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	@Column(name = "longitude", precision = 10, scale = 6)
	public BigDecimal getLongitude() {
		return this.longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	@Column(name = "precision_", precision = 10, scale = 6)
	public BigDecimal getPrecision() {
		return this.precision;
	}

	public void setPrecision(BigDecimal precision) {
		this.precision = precision;
	}

	@Column(name = "ticket", length = 500)
	public String getTicket() {
		return this.ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	@Column(name = "event")
	public String getEvent() {
		return this.event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	@Column(name = "to_open_id")
	public String getToOpenId() {
		return this.toOpenId;
	}

	public void setToOpenId(String toOpenId) {
		this.toOpenId = toOpenId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_time", length = 19)
	public Date getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	@Column(name = "status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "is_delete")
	public Short getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(Short isDelete) {
		this.isDelete = isDelete;
	}

}
