package com.hotpot_ordering.service.Impl;

import com.hotpot_ordering.config.WechatAccountConfig;
import com.hotpot_ordering.dto.OrderDTO;
import com.hotpot_ordering.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @ Author: 陌北有棵树
 * @ Date: 2018/1/8 20:39
 * @ Description:
 */
@Service
@Slf4j
public class PushMessageServiceImpl implements PushMessageService {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatAccountConfig accountConfig;

    @Override
    public void orderStatus(OrderDTO orderDTO) {
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        templateMessage.setTemplateId(accountConfig.getTemplateId().get("orderStatus"));
        //推送给谁，openid
        templateMessage.setToUser("abc");

        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first","请记得收货"),
                new WxMpTemplateData("keyword1","微信点餐"),
                new WxMpTemplateData("keyword2","13123332333"),
                new WxMpTemplateData("keyword3",orderDTO.getOrderId()),
                new WxMpTemplateData("keyword4",orderDTO.getOrderStatusEnum().getMsg()),
                new WxMpTemplateData("keyword5","¥"+orderDTO.getOrderAmount()),
                new WxMpTemplateData("remark","欢迎再次光临")
        );

        templateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            log.error("微信模板消息：发送失败{},",e);
        }
    }
}
