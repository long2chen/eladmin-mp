package me.zhengjie.modules.wx.reset;

import me.zhengjie.modules.wx.service.WechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO 类功能描述
 *
 * @author long2chen
 * @version 1.0
 * @date 2024/3/7 15:42
 */
@RestController
@RequestMapping("/wechats")
public class WechatController {
    @Autowired
    private WechatService service;

    /**
     * 微信有个bug【入参echostr是个String，出参需要数字】，不能返回String类型，需要返回数字类型
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @GetMapping(value = "/checkValid")
    public Long checkValid(String signature, String timestamp,
                             String nonce, String echostr) {
        return service.checkSignature(timestamp, nonce, signature) ? Long.parseLong(echostr)
                : 0;
    }

}