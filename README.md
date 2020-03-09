# 宁可白一点博客搭建记录

## 需求分析

1. 主界面

   1. 导航栏：主页标志、各部分链接、搜索框、发布问题、提示、登陆&个人信息。——通用性，做成单独的组件
   2. 左侧边栏，展示各类标签，实现点击自动搜索
   3. 中间最近话题展示
   4. 右侧边栏留作备用，可以做快速导航

2. 登陆页&注册页

   邮箱注册，验证码激活，有空就实现。

   现在任意注册，不需验证。

   后期加入qq授权逻辑。

3. 用户信息页

## 技术选择

使用纯springboot进行开发。

数据库采用mysql。

数据库访问工具：mybatis，实用mybatis-generator自动化生成数据库访问文件。

实用插件：flyway做数据库迁移、lombok做数据访问简化、spring-boot-devtools做热部署自动重启。

## 进度记录

### 第一天

配置各项内容。

1. 配置maven国内镜像

   为了加快下载速度。

   在你安装的maven目录中conf目录下的setting.xml文件中，找到mirrors标签，在其中添加：

   ```xml
   <mirror>
         <id>alimaven</id>
         <mirrorOf>central</mirrorOf>
         <name>aliyun maven</name>
         <url>http://maven.aliyun.com/nexus/content/repositories/central/</url>
    </mirror>
   ```

2. 安装mysql，并且进行配置：

   数据库支持。

   ```properties
   #application.properties配置
   #mysql配置
   spring.datasource.url=jdbc:mysql://localhost:3306/blog
   spring.datasource.username=root
   spring.datasource.password=25981745
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   
   #服务器配置
   server.port=8000
   server.tomcat.uri-encoding=UTF-8
   ```

2. 安装并使用flyway插件进行数据库版本管理：

   ```xml
   <!--添加插件依赖，并配置相关数据库内容-->
   <plugin>
        <groupId>org.flywaydb</groupId>
        <artifactId>flyway-maven-plugin</artifactId>
        <version>6.3.0</version>
        <configuration>
        		<url>jdbc:mysql://localhost:3306/blog</url>
           <user>root</user>
           <password>25981745</password>
        </configuration>
        <dependencies>
           <dependency>
           	<groupId>mysql</groupId>
             <artifactId>mysql-connector-java</artifactId>
             <version>8.0.19</version>
           </dependency>
        </dependencies>
   </plugin>
   ```

   注意：必须要在resource文件下简历db/migration目录，然后在其中放入.sql文件。

   命名规则：Vx__description.sql，序号必须连贯，且不能修改之前的文件。

   使用以下命令进行版本合并。

   ```bash
   mvn flyway:migrate
   ```

3. 配置热部署自动重启插件：spring-boot-devtools

   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-devtools</artifactId>
   </dependency>
   ```

   可以进行配置：

   ```properties
   #static下的文件不会触发自动重启
   spring.devtools.restart.exclude=static/**
   ```

4. 通过github仓库托管代码
   1. 先创建一个仓库
   2. 然后使用提示的命令配置本地仓库