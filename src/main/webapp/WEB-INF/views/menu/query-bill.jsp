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
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport"
	content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>餐助手-顾客服务</title>
<script type="text/javascript">
	document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
		WeixinJSBridge.call('hideToolbar');
		WeixinJSBridge.call('hideOptionMenu');
	});
</script>
</head>
<body style="margin: 0px; padding: 0px;">

	<h4 class="ui top attached header" style="margin-top: 0px;">${title}
		<div class="ui checkbox czsImage" style="float: right;">
			<input type="checkbox" name="mode"> <label>图文模式</label>
		</div>
	</h4>
	<div class="2 fluid ui buttons">
		<c:if test="${isOwn == 1}">
			<div class="ui active huge button czsOwn">个人订单</div>
			<div class="ui huge button czsGroup">集体订单</div>
		</c:if>
		<c:if test="${isOwn == 0}">
			<div class="ui huge button czsOwn">个人订单</div>
			<div class="ui active huge button czsGroup">集体订单</div>
		</c:if>
	</div>
	<form class="czsOwn" action="menu/free/billQuery.do" method="post">
		<input type="hidden" name="isOwn" value="1"> <input
			type="hidden" name="consumerId" value="${openId}"> <input
			type="hidden" name="isShowImg" value="0">
	</form>
	<form class="czsGroup" action="menu/free/billQuery.do" method="post">
		<input type="hidden" name="isOwn" value="0"> <input
			type="hidden" name="consumerId" value="${openId}"> <input
			type="hidden" name="isShowImg" value="0">
	</form>
	<form class="czsSubmit" action="menu/free/billSubmit.do" method="post">
		<input type="hidden" name="openId" value="${openId}">
	</form>

	<c:if test="${isOwn == 1}">
		<div class="1 fluid ui buttons">
			<div class="ui huge button czsSubmit" style="display: none;">提交订单</div>
		</div>
	</c:if>

	<div class="ui segment attached">
		<div class="ui left aligned one column grid">
			<div class="row">
				<div class="column">
					<div class="ui vertical fluid menu czsMenuList">
						<c:forEach items="${billList}" var="item">
							<div class="ui segment item" id="menu-item-${item.menu_id}">
								<div class="image" style="display: none; margin-bottom: 10px;"
									id="image-div-${item.id}">
									<img style="width: 100%;" src=""
										czz-src="../../../${item.path}640/${item.file_name}"
										onclick="imageHandler('${item.id}')">
									<c:if test="${item.status == 1}">
										<a class="like ui corner label"> <i class="checkmark icon"></i>
									</c:if>
									<c:if test="${item.status == 0}">
										<a class="like ui corner label"> <i
											class="heart empty icon"></i>
									</c:if>
									</a>
								</div>
								<div class="content">
									<div>
										<span class="name" style="font-weight: bold;" onclick="imageHandler('${item.id}')">${item.name}</span>
										<div class="ui huge label"
											onclick="imageHandler('${item.id}')">
											<i class="photo icon"></i>
										</div>
										<div class="ui huge label"
											onclick="introduceHandler('${item.id}')">
											<i class="comment icon"></i>
										</div>
									</div>

									<p class="description" style="display: none;"
										id="introduce-p-${item.id}"
										onclick="introduceHandler('${item.id}')">${item.introduce}</p>
									<div class="ui divider"></div>
									<div style="margin-top: 10px; margin-bottom: 10px;">
										<div class="ui red label">
											<i class="yen icon"></i>${item.price}</div>
										<div class="ui green label">${item.category}</div>
										<div class="ui blue label">${item.taste}</div>
									</div>
									<div class="ui divider"></div>

									<!-- 个人 -->
									<c:if test="${isOwn == 1}">
										<div class="2 fluid ui buttons">
											<c:if test="${item.status == 3}">
												<div class="negative ui button disabled">减一份</div>
												<div class="or"></div>
												<div class="positive ui button disabled">加一份</div>
											</c:if>
											<c:if test="${item.status == 0 || item.status == 1}">
												<div class="negative ui button"
													onclick="billReduceHandler('${item.menu_id}', '${openId}', ${item.price})">
													减一份</div>
												<div class="or"></div>
												<div class="positive ui button" id="hold-ui-btn-${item.id}"
													onclick="billAddHandler('${item.menu_id}', '${openId}', ${item.price})">
													加一份</div>
											</c:if>
										</div>

										<div style="margin-top: 10px;" class="czsCopies">
											<input type="hidden" value="${item.status}"
												class="czsBillStatus">
											<div class="ui label"
												style="margin-top: 5px; margin-bottom: 5px;">
												自己(<span id="item-copies-${item.menu_id}">${item.copies}</span>)
												<div class="detail">
													<c:if test="${item.status == 0}">待提交</c:if>
													<c:if test="${item.status == 1}">已下单</c:if>
													<c:if test="${item.status == 3}">已接受</c:if>
												</div>
											</div>
										</div>
									</c:if>

									<!-- 集体 -->
									<c:if test="${isOwn == 0}">
										<c:forEach items="${item.menuBill}" var="item2">
											<div class="ui label"
												style="margin-top: 5px; margin-bottom: 5px;">
												${item.nickname}(<span id="item-copies-${item.menu_id}">${item.copies}</span>)
												<div class="detail">
													<c:if test="${item.status == 0}">待提交</c:if>
													<c:if test="${item.status == 1}">已下单</c:if>
													<c:if test="${item.status == 3}">已接受</c:if>
												</div>
											</div>
										</c:forEach>
									</c:if>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>

	<c:if test="${isOwn == 1}">
		<div class="1 fluid ui buttons">
			<div class="ui huge button czsSubmit" style="display: none;">提交订单</div>
		</div>
	</c:if>

	<div style="height: 44px;"></div>

	<!-- bottom header -->
	<div class="ui fixed bottom inverted fluid three item menu">
		<a class="item"
			href="menu/free/billQuery.do?isOwn=1&consumerId=${openId}"><i
			class="icon yen"></i><span id="bill-total-span">${total}</span></a> <a
			class="item" href="menu/free/list4bill.do?openId=${openId}"><i
			class="icon align justify"></i>商家菜单</a> <a class="item"
			href="user/free/stowQuery.do?openId=${openId}"><i
			class="icon heart"></i>收藏菜品</a>
	</div>


	<div class="ui modal czsConfirm">
		<div class="header">确认提示</div>
		<div class="content">
			<div class="left">
				<i class="warning icon"></i>
			</div>
			<div class="right" style="font-size: 30px;">
				<p>确认要提交订单吗?</p>
			</div>
		</div>
		<div class="actions">
			<div class="two fluid ui buttons">
				<div class="ui negative labeled icon button">
					<i class="remove icon"></i> 取消
				</div>
				<div class="ui positive right labeled icon button">
					确认 <i class="checkmark icon"></i>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	
		function imageHandler(id){
			$('#image-div-' + id + " > img").each(function(){
				$(this).attr('src', $(this).attr('czz-src'));
			});
			$('#image-div-' + id).toggle();
		}
		
		function introduceHandler(id){
			$('#introduce-p-' + id).toggle();
		}
	
		function billReduceHandler(menuId, consumerId, price) {
			$.post('menu/free/billDeal.do', {
				menuId : menuId,
				consumerId : consumerId,
				status : 2
			}, function(msg) {
				if (msg.succeed) {
					var copies = Number($('#item-copies-' + menuId).text());
					if (copies > 1) {
						$('#item-copies-' + menuId).text(copies - 1);
					} else {
						$('#menu-item-' + menuId).remove();
					}
					
					var total = Number($('#bill-total-span').text());
					$('#bill-total-span').text(format_number(total-=(price), 2));
				} else {
					alert('操作失败!')
				}
			});
		}
	
		function billAddHandler(menuId, consumerId, price) {
			$.post('menu/free/billDeal.do', {
				menuId : menuId,
				consumerId : consumerId,
				status : 1
			}, function(msg) {
				if (msg.succeed) {
					var copies = Number($('#item-copies-' + menuId).text());
					$('#item-copies-' + menuId).text(copies + 1);
					
					var total = Number($('#bill-total-span').text());
					$('#bill-total-span').text(format_number(total+=(price), 2));
				} else {
					alert('操作失败!')
				}
			});
		}

		function format_number(pnumber,decimals){
		    if (isNaN(pnumber)) { return 0};
		    if (pnumber=='') { return 0};
		     
		    var snum = new String(pnumber);
		    var sec = snum.split('.');
		    var whole = parseFloat(sec[0]);
		    var result = '';
		     
		    if(sec.length > 1){
		        var dec = new String(sec[1]);
		        dec = String(parseFloat(sec[1])/Math.pow(10,(dec.length - decimals)));
		        dec = String(whole + Math.round(parseFloat(dec))/Math.pow(10,decimals));
		        var dot = dec.indexOf('.');
		        if(dot == -1){
		            dec += '.';
		            dot = dec.indexOf('.');
		        }
		        while(dec.length <= dot + decimals) { dec += '0'; }
		        result = dec;
		    } else{
		        var dot;
		        var dec = new String(whole);
		        dec += '.';
		        dot = dec.indexOf('.');    
		        while(dec.length <= dot + decimals) { dec += '0'; }
		        result = dec;
		    }  
		    return result;
		}
		
		jQuery(function($) {
			
			$('.ui.checkbox.czsImage').checkbox({
				onEnable : function() {
					$('.ui.menu.czsMenuList .image').show().end().find('img').each(function() {
						$(this).attr('src', $(this).attr('czz-src'));
					});
				},
				onDisable : function() {
					$('.ui.menu.czsMenuList .image').hide();
				}
			});
			
			if('${isShowImg}' == '1'){
				$('.ui.checkbox.czsImage').checkbox('enable');
			}
			
			$('.ui.button.czsOwn').click(function(){
				$('form[class="czsOwn"]').submit();
			});
			$('.ui.button.czsGroup').click(function(){
				$('form[class="czsGroup"]').submit();
			});
			
			$('.ui.button.czsSubmit').click(function(){
				$('.ui.modal.czsConfirm').modal('show');
			});
			
			$('.ui.modal.czsConfirm').modal({
				onApprove : function() {
					$('form[class="czsSubmit"]').submit();
				}
			});
			
			$('input[class="czsBillStatus"]').each(function(){
				if($(this).val() == 0){
					$('.ui.button.czsSubmit').show();
					return false;
				}
			});
			
		});
	</script>
</body>
</html>