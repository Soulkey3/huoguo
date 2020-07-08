package cn.jsut.huoguo.config;

import com.lly835.bestpay.config.AliPayConfig;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BestPayConfig {

    @Bean
    public BestPayService bestPayService(){
        AliPayConfig aliPayConfig=new AliPayConfig();
        aliPayConfig.setAppId(AlipayConfig.APP_ID);
        aliPayConfig.setPrivateKey(AlipayConfig.APP_PRIVATE_KEY);
        aliPayConfig.setAliPayPublicKey(AlipayConfig.ALIPAY_PUBLIC_KEY);
        aliPayConfig.setNotifyUrl(AlipayConfig.notify_url);
        aliPayConfig.setReturnUrl(AlipayConfig.return_url);
        aliPayConfig.setSandbox(true);
        BestPayServiceImpl bestPayService=new BestPayServiceImpl();

        bestPayService.setAliPayConfig(aliPayConfig);
        return bestPayService;
    }



}
