#个人博客网站项目
###博客项目简介
```
本项目是一个基于SSM（Spring+SpringMVC+Mybatis）框架整合开发的博客系统。
界面简洁美观、敏捷开发系统架构
项目代码简洁、注解丰富、上手容易
实现功能点如下：
1、博客列表展示：文章按照时间顺序（时间倒序：最新最先展示）列表展示
2、博客文章详情展示：展示文章全部内容，包含：作者、创建时间、所属目录、标签、文章标题、内容
3、用户权限管理：游客只能浏览文章、管理员可以发布文章、文章下线处理
4、添加文章功能：支持富文本编辑。可以调整字体大小、样式、键入代码等功能
```

###快速启动
 1、下载代码
```
   git地址:
```
2、创建数据库库表
```
    项目目录sql下，有两个sql文件：myblog.sql为数据库表创建sql、test.sql为测试数据
    启动并登陆MySQL数据库，将myblog.sql中的sql拷贝到MySQL数据库执行。
    将test.sql中的数据也拷贝到MySQL数据库执行
```
3、修改配置
```
项目连接数据库，需要先将数据库配置成你本地数据库的配置
在本项目的resource资源目录下的找到jdbc.properties
分别将数据库名、用户名、密码修改为自己数据库相关配置
```
4、部署启动
```
本项目采用将war包部署在Tomcat容器中进行运行，
先配置tomcat容器
然后启动项目
```
5、项目体验
```
前台页面：http://localhost:8080
登录后台：http://localhost:8080/login
账号：liguozheng  密码：12345
```

###开发环境
>开发工具：Intelli IDEA 2019.1.3

>JDK版本：1.8+

>数据库：MySQL 8.0.15

>服务器：Tomcat 8.5.391

>构建工具：Maven 4.0.0

###技术栈
####服务端技术：
>核心框架：Spring:5.1.8.RELEASE

>web框架：SpringMVC：5.1.8.RELEASE

>持久层框架：Mybatis 3.4.6

>数据库连接池：c3p0 0.9.5.4

>JSON数据处理：谷歌gson 2.3

####前端技术
>jsp

>前端框架:[bootstrap](https://www.bootcss.com)

>富文本编辑器:[百度UEditor](https://ueditor.baidu.com/website/onlinedemo.html)

