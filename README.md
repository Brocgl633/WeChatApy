1、BaseMapper定义了增删改查的方法，在项目运行的时候有反射机制动态生成可以被调用的方法

# WxPayController
1、首先创建了WxPayController，调用了WxPayService，并传递了productId作为参数返回支付二维码链接和订单号的map集合
   为了让map返回给前端，且为R的对象，就要在R类上加上@Accessors(chain=true)链式操作的注释
2、在service层所作了两件事：①创建订单；②调用Native支付接口
    ①创建临时订单对象
    ②a.创建HttpPost对象，所传参数是统一下单的接口地址（微信的服务器地址）
      b.因为参数是json类型，所以要用到gson，将参数map转化成字符串类型，并设置在请求体中
      c.执行execute方法，就能把请求发送出去，得到响应体
      d.对响应体的响应码进行分类判断
      
# 签名过程
1、构造签名串
微信支付API v3 要求商户对请求进行签名。微信支付会在收到请求后进行签名的验证。
如果签名验证不通过，微信支付API v3 将会拒绝处理请求，并返回401 Unauthorized。
2、计算签名值
绝大多数编程语言提供的签名函数支持对签名数据进行签名。强烈建议商户调用该类函数，
使用商户私钥对待签名串进行SHA256 with RSA签名，并对签名结果进行Base64编码得到签名值。
                   SHA：摘要算法     RSA：非对称加密
3、设置HTTP头
微信支付商户API v3要求请求通过HTTP Authorization 头来传递签名。
Authorization由认证类型和签名信息两个部分组成。  
       认证类型：目前为WECHATPAY2-SHA256-RSA2048
       签名信息：·发起请求的商户(包括直连商户、服务商或渠道商)的商户号mchid
               ·商户API证书序列号serial_no，用于声明所使用的证书
               ·请求随机串nonce_str
               ·时间戳timestamp
               ·签名值signature（2、中的签名值）       （注：以上五项签名信息，无顺序要求。）
               
# 验签
1、构造验签名串 String message = this.buildMessage(response);
   应答时间戳
   应答随机串
   应答报文主体
2、获取应答签名 
   微信支付的应答签名通过HTTP头Wechatpay-Signature传递，
   对 Wechatpay-Signature的字段值使用Base64进行解码，得到应答签名。
3、验证签名    
   首先，从微信支付平台证书导出微信支付平台公钥
   然后，把签名base64解码后保存
   最后，验证签名 
               



