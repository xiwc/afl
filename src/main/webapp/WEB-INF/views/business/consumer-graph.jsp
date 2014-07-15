<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<link href="../../../resources/semantic/css/semantic.min.css"
	rel="stylesheet" type="text/css">
<script src="../../../resources/js/lib/jquery-2.0.2.min.js"
	charset="utf-8"></script>
<script src="../../../resources/semantic/javascript/semantic.min.js"
	charset="utf-8"></script>
<script src="../../../resources/js/lib/highcharts.js" charset="utf-8"></script>
<script src="../../../resources/js/lib/jquery.tablesort.min.js"
	charset="utf-8"></script>
<script src="../../../resources/js/lib/jquery.tmpl.min.js"
	charset="utf-8"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport"
	content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>餐助手-商家服务</title>
<script type="text/javascript">
	document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
		WeixinJSBridge.call('hideToolbar');
		WeixinJSBridge.call('hideOptionMenu');
	});
</script>
<script id="consumerTrTpl" type="text/x-jquery-tmpl">
<tr>
	<td class=""><img class="ui avatar image" src="{{html headimgurl}}/64">{{html nickname}}({{html sex}})</td>
	<td class="">{{html description}}</td>
	<td class="">{{html country}} {{html province}} {{html city}}</td>
	<td class="number" data-sort-value="{{html times}}">{{html consume_time}}</td>
	<td class="number" data-sort-value="{{html sec_diff}}">{{html diff}}</td>
</tr>
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

	<div>
		<!-- 侧边栏 -->
		<%@ include file="../menu.jsp"%>

		<!-- header -->
		<%@ include file="../header.jsp"%>

		<h4 class="ui top attached header" style="margin-top: 45px;">顾客统计</h4>
		<div class="ui segment attached czsService"></div>

		<!-- footer -->
		<%@ include file="../footer.jsp"%>

		<div class="ui dimmer czsConsumerDetail"
			style="overflow: auto; padding: 16px;">
			<div class="content">
				<div class="center" style="color: black; vertical-align: top;">
					<div class="ui segment" style="text-align: left;">
						<div class="" style="padding-top: 10px;">
							<table class="ui sortable table segment" style="display: table; font-size: 15px;" id="customer-table">
								<thead>
									<tr>
										<th class="">顾客</th>
										<th class="">位置</th>
										<th class="">地址</th>
										<th class="">时间</th>
										<th class="">距今</th>
									</tr>
								</thead>

								<tbody id="consumer-detail-tbody">
								</tbody>
							</table>
						</div>
						<div class="" style="margin-top: 20px;">
							<div class="one fluid ui buttons">
								<div class="ui button"
									onclick="$(this).closest('.ui.dimmer').dimmer('hide');">确定</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		jQuery(function($) {
			
			$('#customer-table').tablesort();

			$('.ui.dimmer.czsMsg').click(function() {
				$('.ui.dimmer.czsMsg > .content').hide();
			});

			$('#menu-item-business-consumer-stat').addClass('active');

			Highcharts.getOptions().colors = Highcharts.map(Highcharts.getOptions().colors, function(color) {
				return {
					radialGradient : {
						cx : 0.5,
						cy : 0.3,
						r : 0.7
					},
					stops : [ [ 0, color ], [ 1, Highcharts.Color(color).brighten(-0.3).get('rgb') ] ]
				};
			});

			$.post("business/consumerGraph.do", {}, function(msg) {

				if (msg.succeed) {

					$('.ui.segment.czsService').highcharts(
							{
								chart : {
									type : 'column'
								},
								title : {
									text : '顾客统计'
								},
								subtitle : {
									text : '按天统计'
								},
								xAxis : {
									categories : msg.value.date,
									labels : {
										rotation : -45,
										align : 'right',
									}
								},
								yAxis : {
									min : 0,
									title : {
										text : '顾客数 (位)'
									}
								},
								credits : {
									enabled : false
								},
								tooltip : {
									shared : true
								},
								plotOptions : {
									column : {
										pointPadding : 0.2,
										borderWidth : 0,
										events : {
											click : function(e) {
												$.post('business/consumerDayGraph.do', {
													date : e.point.category
												}, function(msg) {
													if (msg.succeed) {
														$('#consumerTrTpl').tmpl(msg.value).appendTo(
																$('#consumer-detail-tbody').empty());
														$('.ui.dimmer.czsConsumerDetail').dimmer('show');
														$('body').scrollTop(0);
													} else {
														alert('获取数据失败!');
													}
												});
											}
										}
									}
								},
								series : [ {
									name : '日顾客数',
									data : msg.value.cnt
								}, {
									name : '日顾客数',
									type : 'spline',
									data : msg.value.cnt
								} ]
							});
				} else {
					alert('获取数据失败!');
				}
			}, 'json');
		});
	</script>
</body>
</html>