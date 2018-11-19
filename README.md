# fmanager
fmanager server side

demo:http://www.gushenwo.com

-[client source](https://gitee.com/codefans/admin-cli)

spring rest api project


1.   mybatis 中出现   org.apache.ibatis.builder.IncompleteElementException: Could not find result map  xxx.Obj
     这个是类型不匹配原因，
             要把 :resultMap 改成 resultType
       <select id="findById" parameterType="java.lang.Long"
		resultMap="main.java.com.fmanager.models.User">
	改成
	<select id="findById" parameterType="java.lang.Long"
		resultType="main.java.com.fmanager.models.User">	
		
2.  spring 中  There is no PasswordEncoder mapped for the id "null" 
    passwordEncoder 问题  参考 https://docs.spring.io/spring-security/site/docs/5.0.0.RELEASE/reference/htmlsingle/#troubleshooting

    
    http://localhost:8080/oauth/token?username=user_1&password=123456&grant_type=password&scope=select&client_id=client_2&client_secret=123456


    
 3. 密码方式获取token 
       
        http://localhost:8080/oauth/token?username=fky&password=dsa&grant_type=password&scope=read&client_id=testjwtclientid&client_secret=XY7kmzoNzl100
    
post方式：
     http://localhost:8080/oauth/token
     
     headers: 
		  Content-Type:application/x-www-form-urlencoded
		  authorization: Basic dGVzdGp3dGNsaWVudGlkOlhZN2ttem9OemwxMDA=   
		  // 需要填写这个 不然会跳出一个账号密码对话框 .  值是账号密码的basic 64
	 body:
	    username=fky&password=5f039b4ef0058a1d652f13d612375a5b&grant_type=password
	    
	    c91c03ea6c46a86cbc019be3d71d0a1a