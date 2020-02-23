## 使用JWT完成前后分离项目的登录验证

一般前后端分离的购物网址中，分为管理端与用户端，在管理端一般使用shiro或者是security进行验证，包括是否登录与权限列表等，在用户端，一般是只需要做登录验证。

这里写的是用户端的登录验证，技术实现方式使用jwt。

实现用户登录，注册，修改密码，获取消息（用来验证是否登录成功）

前端文档使用swagger-ui显示，项目启动后打开[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)查看

数据库使用mysql，数据库名为test01，详细数据库表设计及数据信息见附件user.sql