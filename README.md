
# kotlinMvvmDemo

**ViewModel + LiveData + Koin + 协程 + Retrofit**

### 什么是MVVM
MVVM（Model–view–viewmodel）是一种软件架构模式。
    
### 框架的主要构成

- #### 包结构
![Alt text](./rm_src/1599638359944.png)


- #### base
	- 框架结构
	![Alt text](./rm_src/kotlin_mvvm.png)
	
	- BaseRepository //封装服务器请求及返回
	![Alt text](./rm_src/1599636717851.png)
	- BaseViewModel //封装请求操作及LiveData数据同步方法
	![Alt text](./rm_src/1599637173698.png)


- #### di
	- Gson
	![Alt text](./rm_src/1599623732685.png)

    - OkHttp
    ![Alt text](./rm_src/1599623704554.png)

    - Retrofit
    ![Alt text](./rm_src/1599623773927.png)
	
	- IRepositoryManager 
	![Alt text](./rm_src/1599624030508.png)


- #### globalSetting
	- IGlobalConfig //全局的配置
	    - configBaseUrl()
	    - configOkHttpClient()
	- IGlogbalHttpInterceptor //处理 Http 请求和响应结果的处理类,可做添加token、token失效刷新等处理
		- onHttpResultResponse()
		- onHttpRequestBefore()
	- IReposeErrorListener //全局异常统一处理类
		- handleResponseError(t: Throwable)
	- IRepositoryManage //管理网络请求层以及数据缓存层
		 - obtainRetrofitService()
         - obtainCacheService()
### 如何使用该框架
	
1. 实现 IGlobalConfig
	![Alt text](./rm_src/1599630576726.png)
	
2. 实现 IGlogbalHttpInterceptor
	 ![Alt text](./rm_src/1599630741147.png)

3. 实现 IResponseErrorListener
 ![Alt text](./rm_src/1599630841065.png)
 
4. 注入以上实现 
	![Alt text](./rm_src/1599631002650.png)

5. 在Appliction 的onCreate() 中启动注入
	![Alt text](./rm_src/1599631148638.png)

