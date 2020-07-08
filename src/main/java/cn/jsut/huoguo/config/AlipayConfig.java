package cn.jsut.huoguo.config;

import org.springframework.context.annotation.Configuration;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

@Configuration
public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String APP_ID = "2016102400752166";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCxLItMlVSr5HGrX/rI+VilAfwbr7MV1mPwdxxsDXNHifK3DYCkfSqQ+q0Jtp5eJXg/dF9ze4pFtVJK5HFLfLuvbH3eFEi6C1Q3KwYlQZRgslOzZBhAYeomabLJEXqa2AGTNE3jW+ewe1cLQvtpq5poCIR3iyb7zDVH7SaBqcHKyyoCtUGw1BtyGRepJUTASOwx9Yp7KyEnibDrXfllh4M6E13j5ypA5OqqgNiN8tTpLnwviSWKA2bd3xeLsVai9tOqLAbG7g1ki5wZpRU5nCcFuXIPcnLe2T1fiQNx1Tr0Aqk9IDCgaPkQUFbUiz98C8gct3FsyrTVK1UlivC2CtdxAgMBAAECggEAVfU6kClTLyJiU7b5QVBYRZY07mnMBShCzIO9cl8EMw0miNEwPJ3NbRFxqmjDZQHg2Uholuq1JyTVjChRH0Kg/HcyifBlaH3emfVxwdmTV2atXTeO3I2kwIY/i3XDeiSafwS9VTVdry3oCTqyJ45uF1MqkaUdgMSOhRr1t6rYrr4bbtnkmmdwIPOjQL6lfspiTl4t9YugXbkgV1Kh2YSSYnME48jgqyXeWrG1NFE8L1aOjxdKCsWPwH9uFDS52T9lKCxwNvrV5U/k5yJFO3NxLJ7gDzDKkm/BISppqYooQIvZ6h/o72K7xrU9HRvN7il766G8W3EUs0jcpj9pX9ZWwQKBgQDoZMADRlON1720vUKMfjH11EwVS/gnwK6HdWmfao6sVJzUk1WSH03jo2Ib33LahUYQDfEvCFjRMCw6CkbVGYFhgbVQJ4TImvNk2C/DqJwQx8AdgV9msvOgUA69CJmyzC4XuoPgWj+Ba1NLOw8Uwqrbzb+zn7lX8zCZjOLTtuPorQKBgQDDK9gJucqSn7R28zaQeRjrBgcC9FCm8gDTVxPveefmdZpKIzWcvIJfK1F+UNwETgz1y6/qH4H8VRvE98/36GPXrDPqm1Qqy+5hIWNxFOAqmGfzNsJLbc1uSntAKeLbMPnPZJSfwy+HWVy6jVYTZEPCsLKQgspyEiVfh0wrjz2uVQKBgQCLw+QzU/12ZlUiuVyn0FHNzkN/33O8vMZ6k2PVy6lI/Y5gzsd9HTSj/qRHrSiPQe40Q55VMgjwrq86rOmSykck1HSuY0YZvA88DEUVwHc0JF5noGI3CtM5DE0A5YZCoUfrX3Jm4QAmnty2dyuHydgmX+S2bA51Vb5Z6wCUYf9J9QKBgFgnGHgkG7QoQFmttsqAd9nHqikScs9E5UyPuyhiNnUoTx0HVDLMDlKv/SoBma60FjR64KTtwEdZpaZMi/mcVGRYmkZwDA8cdzoV25V66Vkz51e6Nv3cxXn4WDz97kI0l9CtzFdw0KngmfqE1gkCM3zfVHfjK09klM7WDHhsRV7VAoGBAMoD0HYbhNDhwv/I0EJC56B0wmjGL9NJTkOo+bMyhXz4evIRAs3DfFChtyjfGo6f5anGvpTZAZ5knKdQQRdcvpAPj8KMRPVBv9ioUkIqVM7r9ynEkU0DgHDhtAgV998MSZmq0VLLyPTQ+UP9cXVOhytLpOtCok6w6cxN9M6q2Neb";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuf+Z11Cy7CNe3hSdTjozwxr/denXc0woDOijl3/+ZcEWm1FvA3CkxfRCgTOAKSb890PXaUm0FPVKVRCg1Qk5mvHQ4ksaFcHpWmUK2GKI8XOL8n98g95AH9XcdoeZmePY2cbpg33RKi6wH5EIiOy7546fQLiuQ71wAwoMS2ZvdLpdPgpWMP8+7Z07uJGsMKSvlfEbL+LFjQa6ZmpION/J8Ix+284iB2/jaDDarXSN2YAgKn/dXztNWACpLth9311gU2HBhlYsgBDn1U9yhRS+j8w83y9BOMl05Tsddgx02JAVE+aYHens32Qs+6bpsvwV50V4ZMU35VSWO55yV+5n+QIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://g238k7.natappfree.cc/order/notify";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://g238k7.natappfree.cc/tologin2";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String CHARSET = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

