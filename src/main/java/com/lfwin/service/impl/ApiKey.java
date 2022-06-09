package com.lfwin.service.impl;

import com.alibaba.fastjson.JSON;
import com.lfwin.dao.*;
import com.lfwin.pojo.*;
import com.lfwin.utils.CodeMsg;
import com.lfwin.utils.ObjectToMap;
import org.phprpc.util.PHPSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ApiKey {
    private String key;
    @Autowired
    private CodeDeviceDao codeDeviceDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CommerShopDao commerShopDao;

    @Autowired
    private UserCommerDao userCommerDao;
    @Autowired
    private UserProxyDao userProxyDao;
    @Autowired
    private CodeMsg codeMsg;
    @Resource
    private RedisTemplate redisTemplateForThinkPHP;



    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ApiKey() {

    }
    public ApiKey(String key) {
        this.key = key;
    }

    public String getKeyInfo() throws Exception {
        String info=null;
        CodeDevice codeDevice = codeDeviceDao.getByApikey(key);
        if(codeDevice==null){
            codeMsg.reError(3000);
        }
        User user= userDao.getById(codeDevice.getUid());
        if(codeDevice==null){
            codeMsg.reError(3000);
        }




        UserCommer userCommer = userCommerDao.getByUid(codeDevice.getUid());
        if(userCommer==null){
            codeMsg.reError(3000);
        }

        CommerShop commershop = commerShopDao.getById(codeDevice.getSid());
        if(commershop==null){
            codeMsg.reError(3000);
        }else{
            //如果门店没有配置渠道，使用商户配置渠道信息
          /*  String channel = userCommer.getChannel();
            String channel_shop = commershop.getChannel();
            if(channel_shop==null || "".equals(channel_shop)){
                commershop.setChannel(channel);
            }*/
        }
        Map<String, Object> codeDeviceMap = ObjectToMap.convert(codeDevice);
        Map<String, Object> userMap = ObjectToMap.convert(user);
        Map<String, Object> userCommerMap = ObjectToMap.convert(userCommer);
        Map<String, Object> commershopMap = ObjectToMap.convert(commershop);

        String channel = userCommer.getChannel();
        String channel_shop = commershop.getChannel();
        Map<String, Object> channelMap=new HashMap<>();
        if(channel_shop!=null && !"".equals(channel_shop)){
            channelMap.put("channel",JSON.parseObject(channel_shop,Map.class));
        }else if(channel!=null && !"".equals(channel)){
           channelMap.put("channel",JSON.parseObject(channel,Map.class));
        }


        codeDeviceMap.putAll(userMap);
        codeDeviceMap.putAll(userCommerMap);
        codeDeviceMap.putAll(commershopMap);
        codeDeviceMap.putAll(channelMap);
      //  String jsonString = JSON.toJSONString(codeDeviceMap);
        StringBuffer sb=new StringBuffer();
        PHPSerializer psl = new PHPSerializer();
        try {
            byte[] bytes = psl.serialize(codeDeviceMap);
            sb.append(new String(bytes));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        //thinkphp 存储redis序列化规则
        String infos = "think_serialize:"+sb.toString();
       // System.out.println(infos);
        redisTemplateForThinkPHP.opsForValue().set(key,infos);
        return infos;


/*        JSONObject obj=new JSONObject();
        obj.putAll(JSON.parseObject(JSON.toJSONString(codeDevice)));
        obj.putAll(JSON.parseObject(JSON.toJSONString(user)));
        obj.putAll(JSON.parseObject(JSON.toJSONString(userCommer)));
        obj.putAll(JSON.parseObject(JSON.toJSONString(commershop)));
        Map channel = (Map<String,String>)obj.get("channel");





        String infos = obj.toString();
        PHPSerializer psl = new PHPSerializer();
        String s=null;
        try {
            byte[] bytes = psl.serialize(obj);
             s = new String(bytes);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        System.out.println(s);


        redisTemplateForThinkPHP.opsForValue().set(key,s);

        return infos;
        */

    }



    //获取代理商配置参数
    public Map<String,String> getParentProxy(String uid){
        Map<String,String> map=new HashMap<>();
        UserProxy userProxy = userProxyDao.getByUid(uid);
        if(Integer.parseInt(userProxy.getFid())>0){
            getParentProxy(uid);
        }else{
            String fid=userProxy.getFid();
            map.put("fid_"+fid,fid);

        }

        return map;
    }

}
