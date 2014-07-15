<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport"
	content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>餐助手-商家服务</title>
<link href="../../../resources/semantic/css/semantic.min.css"
	rel="stylesheet" type="text/css">
<link href="../../../resources/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link
	href="../../../resources/datetimepicker/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" type="text/css">
<script src="../../../resources/js/lib/jquery-2.0.2.min.js"
	charset="utf-8"></script>
<script src="../../../resources/js/lib/jquery.tablesort.min.js"
	charset="utf-8"></script>
<script src="../../../resources/semantic/javascript/semantic.min.js"
	charset="utf-8"></script>
<script
	src="../../../resources/datetimepicker/js/bootstrap-datetimepicker.min.js"
	charset="utf-8"></script>
<script
	src="../../../resources/datetimepicker/js/bootstrap-datetimepicker.zh-CN.js"
	charset="utf-8"></script>
<script src="../../../resources/bootstrap/js/bootstrap.min.js"
	charset="utf-8"></script>
<script type="text/javascript">
	document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
		WeixinJSBridge.call('hideToolbar');
		WeixinJSBridge.call('hideOptionMenu');
	});
</script>
<script id="joinTrTpl" type="text/x-jquery-tmpl">
</script>
</head>
<body style="margin: 0px; padding: 0px;">

	<div class="ui dimmer czsMsg">
		<div class="content" style="display: none;">
			<div class="center">
				<div class="ui huge message">
					<span></span>
				</div>
			</div>
		</div>
	</div>

	<!-- 侧边栏 -->
	<%@ include file="../menu.jsp"%>

	<!-- header -->
	<%@ include file="../header.jsp"%>

	<h4 class="ui top attached header" style="margin-top: 45px;">
		历史订单
		<div class="circular ui red label">${fn:length(historyMenuBillList)}份</div>
	</h4>
	<div class="ui segment attached">

		<div class="ui segment">
			<div class="">
				<a class="ui label">
					<div id="datetimepickerStart" class="input-append date"
						style="display: inline;">
						<input type="text"></input> <span class="add-on"> <i
							data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
						</span>
					</div>
				</a><a class="ui label">～</a><a class="ui label">
					<div id="datetimepickerEnd" class="input-append date"
						style="display: inline;">
						<input type="text"></input> <span class="add-on"> <i
							data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
						</span>
					</div>
				</a>
			</div>
			<div style="margin-top: 10px;">
				<a class="ui label czsRequest czsStatus" id="czsStatus-3"
					onclick="filterHandler('3')"
					style="margin-top: 5px; margin-bottom: 5px;"> 已接受 ${accept} 份 </a>
				<a class="ui label czsRequestOwn czsStatus" id="czsStatus-1"
					onclick="filterHandler('1')"
					style="margin-top: 5px; margin-bottom: 5px;"> 已提交 ${submited} 份
				</a><a class="ui label czsRequestGroup czsStatus" id="czsStatus-0"
					onclick="filterHandler('0')"
					style="margin-top: 5px; margin-bottom: 5px;"> 待提交 ${submiting}
					份</a> <a class="ui label czsStatus" onclick="filterHandler('2')"
					id="czsStatus-2" style="margin-top: 5px; margin-bottom: 5px;">
					已退订 ${debook} 份 </a> <a class="ui label czsStatus"
					onclick="filterHandler('')" id="czsStatus-"
					style="margin-top: 5px; margin-bottom: 5px;"> 全部 ${total} 份 </a>
			</div>
		</div>
		<table class="ui sortable table segment" style="display: table;">
			<thead>
				<tr>
					<th class="number">序号</th>
					<th class="">菜名</th>
					<th class="number">份数</th>
					<th class="">备注</th>
					<th class="">状态</th>
					<th class="">时间</th>
					<th class="number">距今</th>
					<th class="">顾客</th>
					<th class="">位置</th>
					<th class="">接受者</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${historyMenuBillList}" var="item" varStatus="sts">
					<tr id="item-tr-${item.id}" class="item-tr-${item.menu_id}">
						<td class="">${sts.index + 1}</td>
						<td class="">${item.name}</td>
						<td class="">${item.copies}</td>
						<td class=""><c:if test="${! empty item.memo}"><div class="ui red label">${item.memo}</div></c:if></td>
						<td class=""><c:if test="${item.status==0}">待提交</c:if> <c:if
								test="${item.status==1}">已提交</c:if> <c:if
								test="${item.status==2}">已退订</c:if> <c:if
								test="${item.status==3}">已接受</c:if></td>
						<td class="">${item.date_time}</td>
						<td class="" data-sort-value="${item.sec_diff}">${item.diff}</td>
						<td class=""><img class="ui avatar image"
							src="${item.headimgurl}/64">${item.nickname}(${item.sex})</td>
						<td class="">${item.description}</td>
						<td class=""><c:if test="${! empty item.accept_nickname}">
								<img class="ui avatar image" src="${item.accept_headimgurl}/64">${item.accept_nickname}(${item.accept_sex})</c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<!-- footer -->
	<%@ include file="../footer.jsp"%>

	<script type="text/javascript">
		function filterHandler(status) {
			$('<form action="menu/orderHistory.do" method="post"></form>').append(
					$('<input type="hidden">').attr('name', 'status').attr('value', status)).append(
					$('<input type="hidden">').attr('name', 'start').attr('value',
							$('#datetimepickerStart > input').val()))
					.append(
							$('<input type="hidden">').attr('name', 'end').attr('value',
									$('#datetimepickerEnd > input').val())).submit();
		}

		jQuery(function($) {

			$('table').tablesort().data('tablesort');
			$('thead th.number').data('sortBy', function(th, td, sorter) {
				if (!!$(td).attr('data-sort-value')) {
					return parseInt($(td).attr('data-sort-value'), 10);
				}
				return parseInt(td.text(), 10);
			});

			$('#menu-item-order-history').addClass('active');

			$('#czsStatus-' + '${status}').addClass('green');

			$('#datetimepickerStart').datetimepicker({
				format : 'yyyy-MM-dd hh:mm:ss',
				language : 'zh-CN'
			});

			$('#datetimepickerEnd').datetimepicker({
				format : 'yyyy-MM-dd hh:mm:ss',
				language : 'zh-CN'
			});

			var pickerStart = $('#datetimepickerStart').data('datetimepicker');
			var pickerEnd = $('#datetimepickerEnd').data('datetimepicker');

			var startDate = new Date();
			startDate.setTime(Number('${start}'));
			var endDate = new Date();
			endDate.setTime(Number('${end}'));

			pickerStart.setLocalDate(startDate);
			pickerEnd.setLocalDate(endDate);
		});
	</script>
</body>
</html>