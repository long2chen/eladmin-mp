
package me.zhengjie.modules.wx.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * TODO 类功能描述
 *
 * @author 633725
 * @version 1.0
 * @date 2024/3/7 15:44
 */
@Service
public class WechatService {
    private static final String TOKEN = "long2chen";

    /**
     * 校验 signature(签名) 的正确性
     * a. 将 token、timestamp、nonce 三个参数进行字典序排序
     * b. 将三个参数字符串拼接为一个字符串后进行 sha1 加密
     * c. 把 sha1 加密后的字符串与 signature 的值进行比较
     * d. 若相同, 返回 true; 若不相同, 返回 false
     */
    public boolean checkSignature(String timestamp, String nonce, String signature) {

        String[] params = {TOKEN, timestamp, nonce};
        Arrays.sort(params);

        // b. 将三个参数字符串拼接为一个字符串后进行 sha1 加密
        StringBuilder joinParam = new StringBuilder();
        for (String param : params) {
            joinParam.append(param);
        }

        String secretParam = DigestUtils.sha1Hex(joinParam.toString());

        return secretParam.equals(signature);
    }

}