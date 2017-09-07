package test.wowschuser;

import com.alibaba.fastjson.JSONObject;

import java.util.Calendar;

/**
 * Created by baiyuting on 2017/7/5.
 */
public class Test {

    @org.junit.Test
    public void test(){

        String infoStr = "{\n" +
                "    \"errcode\": 40003,\n" +
                "    \"errmsg\": \"invalid openid hint: [hemT4a0473vr19]\"\n" +
                "}";
        WowschUser resUser = JSONObject.parseObject(infoStr, WowschUser.class);

        String ss ="{\n" +
                "\"touser\": \"%s\",\n" +
                "\"template_id\": \"%s\",\n" +
                "\"url\": \"%s\",\n" +
                "\"data\": {\n" +
                "\"first\": {\n" +
                "    \"value\": \"欢迎进入WowSchool为中国孩子量身定制的自然拼读课，今日课程已新鲜出炉\"\n" +
                "},\n" +
                "\"keyword1\": {\n" +
                "    \"value\": \"自然拼读课 s% %s\"\n" +
                "},\n" +
                "\"keyword2\": {\n" +
                "    \"value\": \"%s\"\n" +
                "},\n" +
                "\"remark\": {\n" +
                "    \"value\": \"\\n点此消息开始今日学习并完成练习作业\\n\\n在学习中，如需帮助请添加小助手微信：wowschool1234\\n退订本消息提醒请回复“TDPD”\\n\\n“自然拼读level。。。”“s%”“退订本消息提醒请回复。。。”\",\n" +
                "    \"color\": \"#6495ED\"\n" +
                "}\n" +
                "}\n" +
                "}";
        String a = "absc";
        a.toUpperCase();
        System.out.println(a);
    }

    @org.junit.Test
    public void test2(){
        String ss = "pdzs+";
        String a = ss.substring(0, 1);
    }

    @org.junit.Test
    public void test3(){
        String.format("%d%%", 100);
    }

    @org.junit.Test
    public void getLastTimeInterval() {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;
        int offset1 = 1 - dayOfWeek;
        int offset2 = 7 - dayOfWeek;
        calendar1.add(Calendar.DATE, offset1 - 7);
        calendar2.add(Calendar.DATE, offset2 - 7);
        calendar1.getTime();
        // System.out.println(sdf.format(calendar1.getTime()));// last Monday
//        String lastBeginDate = sdf.format(calendar1.getTime());
        // System.out.println(sdf.format(calendar2.getTime()));// last Sunday
//        String lastEndDate = sdf.format(calendar2.getTime());
//        return lastBeginDate + "," + lastEndDate;
    }


}
