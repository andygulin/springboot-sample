### SpringBoot With MyBatis

##### 返回所有用户
    URL : /user/all
    METHOD : GET
    
##### 分页返回用户
    URL : /user/page/{pageNo}
    METHOD : GET
    
##### 根据name查询用户
    URL : /user/query/{name}
    METHOD : GET
    
##### 根据ID返回用户
    URL : /user/get/{id}
    METHOD : GET
    
##### 根据ID删除用户
    URL : /user/delete/{id}
    METHOD : DELETE
    
##### 插入用户
    URL : /user/insert
    METHOD : PUT
    BODY : {
               "name":"insert user",
               "age":123,
               "address":"insert address"
           }
    Content-Type : application/json
    
##### 更新用户
    URL : /user/update
    METHOD : PUT
    BODY : {
                "id":1
                "name":"update user",
                "age":123,
                "address":"update address"
              }
    Content-Type : application/json
    
    