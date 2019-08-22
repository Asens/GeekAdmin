## GeekAdmin

采用Springboot2.x，Vue为基础。使用GeekAdmin可以快速的构建简洁且高质量的项目初始版本。

前端使用基于[Ant Design of Vue](https://github.com/vueComponent/ant-design-vue)的组件库

GeekAdmin提供权限管理，分布式定时任务等后台常用功能。

### 使用

Geek Admin采用前后端分离的方式,当前项目为后端,前端为[Geek Admin Front](https://github.com/Asens/GeekAdminFront)

#### 开发

后端和前端推荐jetbrains的IDEA和WebStorm,下面分别以这两种工具进行介绍

打开[Geek Admin](https://github.com/Asens/GeekAdmin)主页,选择Fork或是下载zip,然后导入IDEA

- 选择fork之后 File - New - Project From Version Control - Git 填写自己仓库新fork的Geek Admin项目

- 下载zip的话,解压 File - New - Project From Existing Sources 在文件系统中选择到GeekAdmin一级的目录

打开之后右下角可能会出现Maven的提示,点击Import Changes

可能需要设置JDK,选择已经安装的JDK,系统需求JDK8

系统使用MySQL数据库,启动前先安装并启动MySQL

数据库的配置文件在resources/application-dev.yml中,可以修改数据库连接信息

数据库文件位于resources/sql/geek_admin.sql,在MySQL中创建geek_admin数据库(或是你已经修改的名字),并执行sql文件

选择com.geekutil.Application类,右击,选择 Debug "Application",如果一切正常的话,系统可以正常启动了





