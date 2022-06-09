package com.lfwin.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.*;


@Component
@Scope("prototype")
public class CodeMsg {
    private Integer code;
    private String message;

    private String key;
    public void setKey(String key) {
        this.key = key;
    }

    public CodeMsg setKeyObj(String key) {
        this.key = key;
        return this;
    }

    private Map<Integer,String> codeMsg=new HashMap<>();
    public CodeMsg() {
                codeMsg.put(1002,"暂无记录");
                codeMsg.put(1010,"暂不开放");
                codeMsg.put(1020,"支付未开通");
                codeMsg.put(1030,"支付渠道未配置，请联系客服");
                codeMsg.put(1900,"请在微信或者支付宝中打开扫码");
                codeMsg.put(2900,"请求执行错误");
                codeMsg.put(3000,"apikey错误");
                codeMsg.put(3010,"请求参数错误");
                codeMsg.put(3020,"顾客条码格式错误");
                codeMsg.put(3030,"订单号格式错误");
                codeMsg.put(3040,"订单号不存在");
                codeMsg.put(3050,"请求过于频繁");
                codeMsg.put(3060,"时间参数不支持跨月");
                codeMsg.put(3061,"查询时间不能超过2个月");
                codeMsg.put(3070,"订单未支付成功");
                codeMsg.put(3080,"退款金额不能大于订单支付金额");
                codeMsg.put(3090,"使用官方优惠订单不支持部分退款");
                codeMsg.put(3900,"验签失败");
                codeMsg.put(4001,"退款处理中，点击更新状态更新退款信息");
                codeMsg.put(4800,"订单更新失败，请重新更新订单");
                codeMsg.put(4870,"该商家订单号已存在，请重新下单");
                codeMsg.put(4880,"订单已支付");
                codeMsg.put(4890,"订单已分账");
                codeMsg.put(4900,"订单错误");
                codeMsg.put(4910,"订单已撤销或已退款");
                codeMsg.put(4920,"订单待支付，无法调用退款");
                codeMsg.put(4930,"订单已支付，无法调用撤销");
                codeMsg.put(4940,"该订单不是当前终端收的款，无法执行退款或撤销");
                codeMsg.put(4950,"订单待支付");
                codeMsg.put(4960,"订单已关闭");
                codeMsg.put(4970,"分账订单无法调用退款或撤销");
                codeMsg.put(4980,"分账接收方不存在");
                codeMsg.put(4981,"该订单非分账类型订单");
                codeMsg.put(4990,"退款订单号有误");
                codeMsg.put(5000,"页面错误");
                codeMsg.put(5010,"商家状态不正常");
                codeMsg.put(5020,"代理状态不正常");
                codeMsg.put(5030,"设备商户号不存在或者跟配置不一致");
                codeMsg.put(5060,"店员状态不正常");
                codeMsg.put(6000,"账号或密码不正确");
                codeMsg.put(7000,"预授权已完成");
                codeMsg.put(7010,"预授权已完成，不能撤销");
                codeMsg.put(7020,"订单待支付，不能预授权完成");
                codeMsg.put(7030,"预授权已撤销");
                codeMsg.put(7040,"未执行预授权完成或撤销");
                codeMsg.put(8999,"该固码已被停用，请使用其他支付方式");
                codeMsg.put(9000,"请求渠道通讯失败");
                codeMsg.put(9001,"分账受理失败");
                codeMsg.put(9100,"请求过于频繁");
                codeMsg.put(10000, "SUCCESS");

                //        codeMsg.put(4000,"");//接口返回错
                //       codeMsg.put(9999,"");//自定义错误

    }
    public CodeMsg(Integer code, String message) {
        this.code = code;
        this.message = message;
    }




    /**
     * 错误返回
     * @param code
     * @return
     */

    public String reError(Integer code){
        Map<String,String> ret = new HashMap<>();
        ret.put("version","3.0");
        ret.put("charset","UTF-8");
        ret.put("sign_type","MD5");
        String message = codeMsg.get(code);
        if(message!=null && "".equals(message)){
            ret.put("message",message);
            ret.put("status",Integer.toString(code));
        }else{
            ret.put("code","9999");
            ret.put("message","未知错误");
        }
        return JSON.toJSONString(ret);
    }
    public String reError(Integer code,String message){
        Map<String,String> ret = new HashMap<>();
        ret.put("version","3.0");
        ret.put("charset","UTF-8");
        ret.put("sign_type","MD5");
        ret.put("message",message);
        ret.put("status",Integer.toString(code));
        return JSON.toJSONString(ret);
    }

    public String reError(Integer code,String message,String version,String type){
        Map<String,String> ret = new HashMap<>();
        ret.put("version",version);
        ret.put("charset","UTF-8");
        ret.put("sign_type",type);
        ret.put("message",message);
        ret.put("status",Integer.toString(code));
        return JSON.toJSONString(ret);
    }

    /**
     * 成功返回
     * @param maps
     * @return
     */
    public String reSuccess(Map<String,String> maps){
        Map<String,String> ret = new HashMap<>();
        ret.put("version","3.0");
        ret.put("charset","UTF-8");
        ret.put("sign_type","MD5");
        ret.put("status","SUCCESS");
        Iterator<Map.Entry<String, String>> iterator = maps.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry < String, String > entry = iterator.next();
            ret.put(entry.getKey(),entry.getValue());
        }
        String sign= signByMd5(ret);
        ret.put("sign",sign);

        return JSON.toJSONString(ret);
    }




    public String signByMd5(Map<String,String> maps){
        StringBuffer sb=new StringBuffer();

        //集合转化成list
        List<Map.Entry<String, String>> infos=new ArrayList<>(maps.entrySet());
        // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
        Collections.sort(infos, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return (o1.getKey()).compareTo(o2.getKey());
            }
        });


        Iterator<Map.Entry<String, String>> iterator = infos.iterator();
        while (iterator.hasNext()) {
            Map.Entry < String, String > entry = iterator.next();
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
            sb.append("&");
        }
        int len = sb.length();
        if(len>0){
            sb.append("signkey="+key);
        }
        System.out.println(sb);
        String signMd5 = DigestUtils.md5DigestAsHex(sb.toString().getBytes());
        return signMd5;
    }






}
