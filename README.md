afl
===

ask for leave


2014/3/24
#1:解决linux下mysql数据库中文乱码问题.
#2:针对系统事件发邮件进行可配置开关.

2014/3/25
#商家入驻
#台桌二维码生成,网页打开,发送图片链接地址到邮箱,打包附件下载!
#台桌二维码批量生成,考虑命名用ticket_senceid_name.jpg

2014/3/26
#来自微信服务器的消息要回复,否则微信服务器会重发三次.
#对于菜单事件通过回复,不是发送客服消息,因为客服消息每天有发送限制.
#商户信息完善:联系电话.
#将二维码链接发送到用户邮箱(批量生成时).
#对二维码进行生成功能分类别,区别不同扫描触发事件场景.
#对access token进行记录,每次获取都写入配置中,以便下次系统启动重复利用,因为有回去次数限制.

#未入驻时,需要先入驻后才能试用二维码生成.
#二维码试用个数限制.初定5个.
#二维码配置界面要进行二维码个数限制.

2014/3/27
二维码批量打包生成发送.
#食客扫描进入商家系统.
#商家菜单登记.(打折.优惠.积分)
二维码购买页面.==>buy.jsp
#qrcode表中加入 二维码的自己服务器url.


商家二维码管理页面,list查看;下载;释放;续约时间.

获取结账消费码
食客评论.
二维码分类型     台桌-结账
获取菜谱

#提示光顾本店=>光顾[店名]
二维码扫描次数累加,判断达到限制.

返回消费时,附带结账链接.
#user表中记录消费码.

#商家PC终端登录链接->发送链接到邮箱,告知登录动态码

一个菜可以对应多张图片


2014/03/31
bugfixed:添加菜单 禁用不勾选添加失败!

2014/04/02
#资源图片删除功能添加.

#菜单修改页面.
#商家信息完善.
#顾客一览实现.

#顾客结账->结账请求->返回消费金额&消费详情链接&确认结账->商家接收到结账请求->确认结账.

#点菜->塞选 分类 口味 同桌点餐情况.

二维码管理
#结账确认
退订确认
服务员
后厨

顾客一览播放声音

台桌视图
账目统计
菜目点击统计
服务统计

二维码申请管理(数量有限) 购买的使用次数,次数使用完,自动回收.

评价功能模块 - 商家 -顾客.

菜点评
菜收藏
#商家收藏.

平台分享推广.
商家店铺推广, 推广页面分享-美食分享推广.

商家人员角色设置管理. 店主-前台收纳-服务员-后厨.

获取商家地理位置.
菜谱排序.

打印消费单.

顾客一览 点击姓名弹出详细信息.

商家给顾客发消息.

bug:首次扫描进入 插入两条用户信息
中间顾客换桌处理  考虑 让前天操作换桌,一次性所有人切换.
bug:个人&集体结账可以同时申请
bug:扫描二维码时,个人信息没有采集到
bug:菜单修改修改分类&口味新增加方式


自己平台推广...


服务平台管理后台...

考虑到用户的手机流量有限,图片的加载方式策略.图片大小缩放处理.减少顾客流量消耗,加快页面加载时间.

二维码打印.
二维码使用状态-是否在使用中.

顾客服务-我的收藏 :  收藏店铺 收藏美食

#乱码问题  原因:form get提交中文==>全部用post提交.

#没有顾客 顾客一览布局问题

#二维码发送失败

#不要禁止扫描

二维码配置生成 进行添加个数限制.

商家上传图片介绍,可以多个,但要进行个数限制.方便顾客分享查看.

菜品图片修改描述.
上传菜品图片进行多个大小版本处理.一般不同网络环境使用.
常开页面session过期问题.

图片批量上传.

结账时,商家帮顾客退掉未上的菜.

顾客一览设定一个起始时间.例如今天的开始营业时间.

添加菜品时  提供 特色 新增 招牌  最热  实惠 精品

通过消费中顾客数量判断店铺的余桌量


顾客结账后,商家发送一个链接,顾客点击进入后,可以对自己吃的菜进行评价,然后对商家有个总体评价.

打印错误:
消息重读,key:okPUDtzjrogWsWdsI_Fz39VNbgBc_1397708583

一桌的人数,餐具计价的.
点菜+备注 微辣 不要加什么....

商家原有网站链接.
商家标签管理.

商家基本信息完善 不在弹出界面完善信息,直接修改.

分配角色 提示成功

订单一览位置链接到菜单.

订单 显示文字状态.

先待定 >> 再次提交确认
收藏 >> 待定.

订单里的 顶部的开关考虑切换  个人消费 <> 集体消费

订单接受时要比对 份数是否不再一致
