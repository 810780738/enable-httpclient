httpclient:
	config:
	#连接超时时间设置
		keepAliveSecond: 30
		#重试次数
		retryFrequency: 3
		#最大连接数
		connectionMaxTotal: 20
		#最大路由基数
		maxPerRoute: 20
		#长连接保持时间 单位s
		timeToLive: 30
		#连接超时 ms
		connectionTimeOut: 3000
		#请求超时 ms
		connectionRequestTimeOut: 3000
		#服务器响应超时时间 ms
		socketTimeOut: 3000
		#请求地址和端口
		hostAndPort: localhost:8080
		#是否使用代理 默认不使用
		enableProxy: false
		#代理服务器IP
		proxyHost: 
		#代理服务器端口
		proxyPort: 
