# jwt(json web tokens)

## 什么是jwt
JSON Web Token是一个开放标准(RFC 7519),它定义了一种紧凑的、自包含的方式,用于作为JSON对象在各方之间安全地传输信息.该信息可以被验证和信任,因为它是数字签名的.


## jwt结构
JSON Web Token由三部分组成,它们之间用圆点(.)连接,典型的例子xxxxx.yyyyy.zzzzz
1. Header
```
header典型的由两部分组成:token的类型("JWT")和算法名称(比如:HMAC SHA256或者RSA等等)
{
"alg":"HS256",
"typ":"JWT"
}
然后,用Base64对这个JSON编码就得到JWT的第一部分
```


2. Payload
```
JWT的第二部分是payload,它包含声明(要求).声明是关于实体(通常是用户)和其他数据的声明.声明有三种类型:registered, public和private.
对payload进行Base64编码就得到JWT的第二部分
```


3. Signature
```
为了得到签名部分,必须有编码过的header、编码过的payload、一个秘钥,签名算法是header中指定的那个,然对它们签名即可.
HMACSHA256(base64UrlEncode(header) + "." + base64UrlEncode(payload), secret)
签名是用于验证消息在传递过程中有没有被更改，并且，对于使用私钥签名的token，它还可以验证JWT的发送方是否为它所称的发送方
```

## jwt是如何工作的
```
在认证的时候，当用户用他们的凭证成功登录以后，一个JSON Web Token将会被返回。此后，token就是用户凭证了，你必须非常小心以防止出现安全问题。一般而言，你保存令牌的时候不应该超过你所需要它的时间。

无论何时用户想要访问受保护的路由或者资源的时候，用户代理（通常是浏览器）都应该带上JWT，典型的，通常放在Authorization header中，用Bearer schema
```

1. 基于服务器的身份认证

2. 基于Token的身份认证是如何工作的




## jwt与session的差异
```
相同点是，它们都是存储用户信息；然而，Session是在服务器端的，而JWT是在客户端的。
Session方式存储用户信息的最大问题在于要占用大量服务器内存，增加服务器的开销。
而JWT方式将用户状态分散到了客户端中，可以明显减轻服务端的内存压力。
Session的状态是存储在服务器端，客户端只有session id；而Token的状态是存储在客户端
```


## jwt与OAuth的区别
```
OAuth2是一种授权框架 ，JWT是一种认证协议
无论使用哪种方式切记用HTTPS来保证数据的安全性
OAuth2用在使用第三方账号登录的情况(比如使用weibo, qq, github登录某个app)，而JWT是用在前后端分离, 需要简单的对后台API进行保护时使用
```




1. 实时上报定位信息，实时地图展现
根据人员上报的定位信息，实时的在地图上展现，延时在2s内

2. 设置异常级别，异常提醒
根据出现的异常情况，实时短信提醒或其他方式提醒(硬件设备鸣叫，闪红灯等等)


3. 车辆进出登记
实现车辆的车牌识别，出入的自动登记


4. 数据统计分析
根据记录的信息进行实时统计分析(异常次数,车辆来访次数等)




@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(60000);
        requestFactory.setReadTimeout(60000);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        List<HttpMessageConverter<?>> list = restTemplate.getMessageConverters();
        for (HttpMessageConverter<?> httpMessageConverter : list) {
            if (httpMessageConverter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) httpMessageConverter).setDefaultCharset(Charset.forName("UTF-8"));
            }
        }
        return restTemplate;
    }
}


ExecutorService exec = new ThreadPoolExecutor(1, psIdsList.size(),
        60L, TimeUnit.SECONDS,
        new SynchronousQueue<Runnable>()
);
for (int i = 0; i < psIdsList.size(); i++)
{
    final List<Long> processList = new ArrayList<>();
    processList.addAll(psIdsList.get(i));
    Runnable task = new Runnable()
    {
        @Override
        public void run()
        {
            try
            {
                batchProcess(epgGroup,processList,catgIdList, mongoChargeInfoMap,quoteEpgGroupId);
            }
            catch (Exception e)
            {
                log.error("MongoEpgGroupPsServiceImpl--execCalc--error:", e);
            }
        }
    };
    exec.submit(task);
}
exec.shutdown();
while (true)
{
    if (exec.isTerminated())
    {
        break;
    }
    try
    {
        Thread.sleep(1000);
    }
    catch (Exception e)
    {
        //ignore
        break;
    }

}





