package com.heitian.ssm.controller;

import com.heitian.ssm.model.*;
import com.heitian.ssm.service.*;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import com.heitian.ssm.service.ReadExcelUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;


@Controller
@RequestMapping("/user")
public class UserController {

    private Logger log = Logger.getLogger(UserController.class);
    @javax.annotation.Resource
    private UserTestService userTestService;
    @javax.annotation.Resource
    private UserService userService;
    @javax.annotation.Resource
    private ManagerService managerService;
    @javax.annotation.Resource
    private ResourceService resourceService;
    @javax.annotation.Resource
    private LogRecordService logRecordService;
    @javax.annotation.Resource
    private BehaviorService behaviorService;
    @javax.annotation.Resource
    private BehaviorAnaService behaviorAnaService;
    @javax.annotation.Resource
    private UniversitySchoolService universitySchoolService;

//自己写着玩玩：跳转到登录界面login.jsp
    @RequestMapping("/toLogin")
    public String toLogin() {
        //log.info("跳转到登录界面login.jsp");
        return "login";
    }

//自己写着玩玩：跳转到注册成功界面
    @RequestMapping("/registerResult")
    public String registerResult(Model model,HttpServletRequest request) {
        //log.info("跳转到注册成功界面registerResult.jsp");
        String userId = request.getParameter("username_register");
        String password1 = request.getParameter("password_register1");
//这个没搞好！！！！！没切换成User
        //userTestService.addUser(username,password1);

        User user = new User();
        user.setUserId(userId);user.setUserPassword(password1);
        userService.insert(user);//这个是向数据库中实际录入的操作！

        model.addAttribute("username",userId);
        model.addAttribute("password1",password1);
        return "registerResult";
    }

    //教师登录：响应和接收王阿凤端的请求和数据
    @RequestMapping("/teaLogin")
    public void teaLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //拿到王阿凤端的用户名和密码！极为重要！！！
        String mId = request.getParameter("ID");
        String mPassword = request.getParameter("password");
        String activity = request.getParameter("activity");

        log.info("管理员【"+mId+"】尝试登陆！管理员密码："+mPassword+"； 活动："+activity);
        //设置返回王阿凤端的通道！！！response！！
        response.setCharacterEncoding("UTF-8");response.setContentType("text/html");PrintWriter out = response.getWriter();

        List<Manager> logManagers = managerService.selectByIdAndPassword(mId,mPassword);

        //根据匹配结果 返回给王阿凤端 true 或者 false
        if ((!logManagers.isEmpty())&&logManagers.get(0).getmKey()==1){
            log.info("管理员【"+mId+"】—登陆成功！好厉害哦！");
            out.print(true);}
        else{
            log.info("管理员【"+mId+"】—登陆失败！好遗憾啊");
            out.print(false);}


        out.flush();
        out.close();
    }


    //教师注册：响应和接收王阿凤端的请求和数据
    @RequestMapping("/teaRegister")
    public void teaRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //拿到王阿凤端的用户名和密码！极为重要！！！
        String mId = request.getParameter("teaID");
        String m_password2 = request.getParameter("repassword");
        String m_password1 = request.getParameter("password");
        String m_name = request.getParameter("name");
        String m_sex = request.getParameter("sex");
        String m_phone = request.getParameter("tel");
        String m_university = request.getParameter("university");
        String m_school = request.getParameter("school");
        log.info("新管理员尝试注册！—— id："+mId+"； 密码："+m_password1);
        //设置返回王阿凤端的通道！！！response！！
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();


        //去找数据库找是否已经存在该管理员~
        Manager manager = managerService.selectByPrimaryKey(mId);

        //根据匹配结果 返回给王阿凤端 true 或者 false
        if(mId.equals("")||m_password2.equals("")||m_password1.equals("")||m_name.equals("")||m_sex.equals("")||m_phone.equals("")||m_university.equals("")||m_school.equals("")){
            System.out.println("************信息填写不完整！************");
            out.print("buwanquan");
        }else if(manager !=null){
            System.out.println("************该管理员名已存在！************");
            out.print("existed");}
        else if (!m_password1.equals(m_password2)){
            System.out.println("************两次密码不一致！************");
            out.print("unmatch");}

        else{
            System.out.println("************注册审核中！************");
            //创建对象记录进数据库
            int uni_id,sch_id;
            List<UniversitySchool> u = universitySchoolService.selectByUniversityAndSchool(m_university,m_school);
            if (u!=null){
                uni_id = u.get(0).getUniId();
                sch_id = u.get(0).getSchId();
            }else {uni_id=0;sch_id=0;}

            Manager manager1 = new Manager();
            manager1.setmId(mId);manager1.setmPassword(m_password1);manager1.setmKey(0);
            manager1.setmSex(m_sex);manager1.setmPhone(m_phone);manager1.setmName(m_name);
            manager1.setmUniversity(uni_id+"");manager1.setmSchool(sch_id+"");
            managerService.insert(manager1);//这个是向数据库中实际录入的操作！

            out.print(true);}


        out.flush();
        out.close();
    }


    //确认教师注册信息，并执行数据插入
    @RequestMapping("/permitManage")
    public String permitManage(Model model,HttpServletRequest request, HttpServletResponse response) {
//返回待审核的管理员信息
        List<Manager> managerList = managerService.selectByIsKey(0);
        //String userId = request.getParameter("username_register");

        model.addAttribute("managerList",managerList);
        return "registerResult";
    }


    //确认教师注册信息，并执行数据插入
    @RequestMapping(value= "/permitOneManage" , method= RequestMethod.GET)
    public String permitOneManage(@RequestParam("status") String status,@RequestParam("mId") String mId, Model model, HttpServletRequest request, HttpServletResponse response) {
//返回待审核的管理员信息
      //  List<Manager> managerList = managerService.selectByPrimaryKey("1");
        String s ;
        if(status.equals("1")){s="您已确认用户**通过审核！"+mId;}else {s = "您已拒绝**的管理员注册申请"+mId;}
        model.addAttribute("s",s);
        return "registerResult";
    }



//教师check所管学生列表
    @RequestMapping("/checkList")
    public void checkList(HttpServletRequest request, HttpServletResponse response) throws IOException {
System.out.println("我是第一行");
        String mId = request.getParameter("tea_ID");

        response.setCharacterEncoding("UTF-8");response.setContentType("text/html");PrintWriter out = response.getWriter();

        //更新学生数据库的预警
        warnManage();

        //去找数据库找是否已经存在该管理员~
        Manager manager = managerService.selectByPrimaryKey(mId);

        int m_university = Integer.parseInt(manager.getmUniversity());
        System.out.println("我是第二行"+m_university);
        int m_school = Integer.parseInt(manager.getmSchool());
        System.out.println("我是第二行"+ m_school);
        List<User> userList = userService.selectUserByUniversityAndSchool(m_university,m_school);
        System.out.println("我是第二行"+  userList);

System.out.println("我是第二行++++");

//自创列表传输形式*************************
        String allList = "";
        String yujing = "是";
System.out.println("我是第三行");
        for (int k=0;k<userList.size();k++) {
            if(userList.get(k).getIsWarn().equals("0")){yujing = "否";}else yujing = "是";
            allList = allList+"{"+userList.get(k).getUserId()+","+userList.get(k).getUserName()+","+yujing+"}";
        }
        System.out.println(allList);
        out.println(allList);

//**************************

        out.flush();
        out.close();
    }


    //查询单个学生的记录
    @RequestMapping("/checkStudent")
    public void checkStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //拿到王阿凤端的用户名和密码！极为重要！！！
        String userId = request.getParameter("student_ID");

        //设置返回王阿凤端的通道！！！response！！
        response.setCharacterEncoding("UTF-8");response.setContentType("text/html");PrintWriter out = response.getWriter();

        List<BehaviorAna> behaviorAnaList = behaviorAnaService.selectByUser(Integer.parseInt(userId));

        //*************************自创列表传输形式
        String allList = "时间                             不同度             \n";
        for (int k=0;k<behaviorAnaList.size();k++) {
            allList = allList+"   "+behaviorAnaList.get(k).getAnaTime()+","+behaviorAnaList.get(k).getStandard()+"     \n";
        }
        System.out.println(allList);
        out.println(allList);
        //**************************

        out.flush();
        out.close();
    }



//学生登录：响应和接收王阿凤端的请求和数据
    @RequestMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {

    //拿到王阿凤端的用户名和密码！极为重要！！！
        String userId = request.getParameter("ID");
        String password = request.getParameter("password");
        String activity = request.getParameter("activity");
        String style = request.getParameter("style");
        String allStyle; if(style.equals("s")) {allStyle = "学生";}else {allStyle = "管理员";}
        log.info("用户【"+userId+"】尝试登陆！用户密码："+password+"； 身份："+allStyle+"； 活动："+activity);
    //设置返回王阿凤端的通道！！！response！！
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
    //去找数据库匹配~
        //List<UserTest> logUserTests = userTestService.selectUserByIdAndPassword(username,password);
        List<User> logUsers = userService.selectUserByIdAndPassword(userId,password);
    //创建对象记录进数据库
        LogRecord logRecord = new LogRecord();
        logRecord.setUserId(userId);
        logRecord.setLogType(3);// 数据库记录结果，3代表登录！！
        logRecord.setLogTime(new Date());

        //根据匹配结果 返回给王阿凤端 true 或者 false
       if (!logUsers.isEmpty()){
           logRecord.setLogResult(1);//数据库记录结果，1代表成功
           log.info("用户【"+userId+"】—登陆成功！好厉害哦！");
            out.print(true);}
       else{
           logRecord.setLogResult(0);//数据库记录结果，0代表失败
           log.info("用户【"+userId+"】—登陆失败！好遗憾啊");
            out.print(false);}
        ;
        logRecordService.insert(logRecord);

       out.flush();
       out.close();
    }



//学生注册：响应和接收王阿凤端的请求和数据
    @RequestMapping("/register")
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {


    //拿到王阿凤端的用户名和密码！极为重要！！！
        String userId = request.getParameter("stuID");
        String activity = request.getParameter("activity");
        String user_password2 = request.getParameter("repassword");
        String user_password1 = request.getParameter("password");
        String user_name = request.getParameter("name");
        String style = request.getParameter("style");
        String user_sex = request.getParameter("sex");
        String user_phone = request.getParameter("tel");
        String user_university = request.getParameter("university");
        String user_school = request.getParameter("school");
        String allStyle; if(style.equals("s")) {allStyle = "学生";}else {allStyle = "管理员";}
        log.info("有用户尝试注册！—— id："+userId+"； 密码："+user_password1+"； 身份："+allStyle+"； 活动："+activity+"       "+user_university+"     "+user_school+"     "+user_sex);
    //设置返回王阿凤端的通道！！！response！！
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();


    //去找数据库找是否已经存在该用户~
        User user = userService.selectByPrimaryKey(userId);

    //创建对象记录进数据库
        LogRecord logRecord = new LogRecord();
        logRecord.setUserId(userId);
        logRecord.setLogType(1);// 数据库记录结果，1代表注册！！
        logRecord.setLogTime(new Date());
    //根据匹配结果 返回给王阿凤端 true 或者 false
        if(userId.equals("")||user_password2.equals("")||user_password1.equals("")||user_name.equals("")||user_sex.equals("")||user_phone.equals("")||user_university.equals("")||user_school.equals("")){
            logRecord.setLogResult(0);//数据库记录结果，0代表失败
            System.out.println("************信息填写不完整！************");
            out.print("buwanquan");
        } else if(user !=null){
            logRecord.setLogResult(0);//数据库记录结果，0代表失败
            System.out.println("************该用户名已存在！************");
            out.print("existed");}
        else if (!user_password1.equals(user_password2)){
            logRecord.setLogResult(0);//数据库记录结果，0代表失败
            System.out.println("************两次密码不一致！************");
            out.print("unmatch");}

        else{
            int uni_id,sch_id;
            List<UniversitySchool> u = universitySchoolService.selectByUniversityAndSchool(user_university,user_school);
            if (u!=null){
                uni_id = u.get(0).getUniId();
                sch_id = u.get(0).getSchId();
            }else {uni_id=0;sch_id=0;}

            logRecord.setLogResult(1);//数据库记录结果，1代表成功
            System.out.println("************注册成功！************");
            User user1 = new User();
            user1.setUserId(userId);user1.setUserPassword(user_password1);user1.setUserPhone(user_phone);
            user1.setUserSex(user_sex);user1.setUserName(user_name);
            user1.setUserUniversity(uni_id);user1.setUserSchool(sch_id);

            userService.insert(user1);//这个是向数据库中实际录入的操作！
            out.print(true);}

        logRecordService.insert(logRecord);

        out.flush();
        out.close();
    }



    //返回行为记录
    @RequestMapping("/returnArticleList")
    public void returnArticleList(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String uid = request.getParameter("user_ID");
        String article_Id = request.getParameter("articleID");
        String type = request.getParameter("user_type");

        int i = 2;
        if(article_Id!=null)  i = Integer.parseInt(article_Id);
        if(type.equals("student")) { //如果为student 表示是学生进入浏览，则记录行为
            Behavior behavior = new Behavior();
            behavior.setbUserId(uid);
            behavior.setbRId(i);
            behavior.setbTime(new Date());
            behavior.setbType(2);//规定“返回主界面”行为编号为 2
            //behavior.setbTestresult();
            behaviorService.insert(behavior);
        }

        response.setCharacterEncoding("UTF-8");response.setContentType("text/html");PrintWriter out = response.getWriter();

        System.out.println("\n>>>用户【"+uid+"】的返回行为已记录！从文章【"+i+"】返回主界面！************");

        out.print("返回成功");
        out.flush();
        out.close();
    }



//单个文章：响应王阿凤端的请求和发送文章内容
    @RequestMapping("/articleContent")
    public void articleContent(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String uid = request.getParameter("user_ID");
        String article_Id = request.getParameter("articleID");
        String activity = request.getParameter("activity");
        String type = request.getParameter("user_type");
        String RorT = request.getParameter("RorT");

        //获取需求的文章序号！！！！要记得赋值！！
        int i = 2;
        if(article_Id!=null)  i = Integer.parseInt(article_Id);
        //
        //去找数据库找文章
        Resource resource = resourceService.selectByPrimaryKey(i);


        //自创形式数据传输   —— String readMore = "{"+resource.getrId()+","+resource.getrTopic()+","+resource.getrTitle()+","+resource.getrContent()+"}";
        String readMore = resource.getrContent();

        response.setCharacterEncoding("UTF-8");response.setContentType("text/html");PrintWriter out = response.getWriter();

        System.out.println("\n>>>文章【"+i+"】已发送至"+uid+"！！activity值为："+activity+"  ************");

        System.out.println(readMore);
        out.print(readMore);
        System.out.println("________________RorT 为"+RorT);
        //记录用户行为到  behavior 表中
        if(type.equals("student")) { //如果为student 表示是学生进入浏览，则记录行为
            Behavior behavior = new Behavior();
            behavior.setbUserId(uid);
            behavior.setbRId(i);
            behavior.setbTime(new Date());
            if(RorT.equals("I")) behavior.setbType(3);//规定“进入测试”行为编号为 3
            else behavior.setbType(1);//规定“进入文章”行为编号为 1
            //behavior.setbTestresult();

            behaviorService.insert(behavior);
        }
        out.flush();
        out.close();
    }



//文章列表：响应王阿凤端的请求和发送所有文章列表
    @RequestMapping("/articleList")
    public void articleList(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String uid = request.getParameter("user_ID");
        String activity = request.getParameter("activity");
        String topicChose = request.getParameter("topicChose");

        //转换topic为数字   i=0时，表示topic为 null;    i=-10000时，表示topic 为 存在错误值
        int i = 0;
        if(topicChose!=null) {if (topicChose.equals("A")) {i = 1;} else if (topicChose.equals("B")) {i = 2;} else if (topicChose.equals("C")) {i = 3;} else if (topicChose.equals("D")) {i = 4;} else if (topicChose.equals("E")) {i = 5;} else if (topicChose.equals("F")) {i = 6;} else if (topicChose.equals("G")) {i = 7;} else if (topicChose.equals("H")) {i = 8;}else if(topicChose.equals("I")) {i=9;}else {i = -10000;}}
        System.out.println("\n>>>接收到两个参数值：id = "+uid+" ；activity = "+activity+" ；topicChose = "+topicChose+"("+i+")");

        //去找数据库按类别ABCDEFG找文章
        List<Resource> resources = resourceService.selectByTopic(i+"");

        response.setCharacterEncoding("UTF-8");response.setContentType("text/html");PrintWriter out = response.getWriter();

        System.out.println(">>>"+topicChose+"类的全部文章发送完毕！*********** ");

//自创列表传输形式
//*************************
        String allList = "";
        //for (int k=0;k<resources.size();k++) {
        for (int k=0;k<10;k++) {
            allList = allList+"{"+resources.get(k).getrId()+","+resources.get(k).getrTitle()+"}";
        }
        System.out.println(allList);
        out.println(allList);
//**************************
        out.flush();
        out.close();
    }


//从苏泽森excel表中导入数据库的操作
    @RequestMapping("/daoru")
    public String daoru(Model model) {
        try {
            String filepath = "G:\\数据抓取\\9测试.xlsx";
            ReadExcelUtils excelReader = new ReadExcelUtils(filepath);
            // 对读取Excel表格标题测试
//          String[] title = excelReader.readExcelTitle(); System.out.println("获得Excel表格的标题:");for (String s : title) {System.out.print(s + " ");}
            // 对读取Excel表格内容测试
            Map<Integer, Map<Integer,Object>> map = excelReader.readExcelContent();
            System.out.println("获得Excel表格的内容:");
            for (int i = 1; i <= map.size(); i++) {
                System.out.println(">>>"+i+"："+map.get(i).get(11));System.out.println(">>>"+i+"："+map.get(i).get(12));
                Resource resource = new Resource();
               // resource.setrId(i);
                resource.setrTopic(9);
                resource.setrTitle((String) map.get(i).get(11));
                resource.setrContent((String) map.get(i).get(12));
                resourceService.insert(resource);
                //System.out.println(map.get(i));
            }
        } catch (FileNotFoundException e) {
            System.out.println("未找到指定路径的文件!");
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }



        return "registerResult";
    }


//网页的登录和信息显示
    @RequestMapping("/showUser")
    public String showUser(Model model, HttpServletRequest request) throws IOException {
        log.info("查询所有用户信息");

        String userId = request.getParameter("username_login");
        String password = request.getParameter("password_login");

        //登录状态judgeState默认值为失败
        String judgeState = "账号或密码错误，请重新登录";
        String succ = "你好，登录成功！";

        List<User> logUsers = userService.selectUserByIdAndPassword(userId,password);

        if (!logUsers.isEmpty()){judgeState = succ;}

        model.addAttribute("judgeState",judgeState);

        System.out.println("\n我哈哈哈哈哈哈\n用户是："+userId+"\n密码是："+password+"\n请求URL是："+request.getRequestURI());

        //以下为胡乱打印的一些数据库参数
        List<User> userList = userService.selectAllUser();
        User firstUser = userService.selectByPrimaryKey("123");
        model.addAttribute("userList", userList);
        model.addAttribute("firstUser", firstUser);
        return "showUser";

    }



//自己写着玩玩：跳转到注册成功界面
    @RequestMapping("/manage")
    public String manage(Model model) {
        Manager manager = managerService.selectByPrimaryKey("003");
//__________________________-___________________________

//        遍历map
//        123 ：0.77
//        119 ：0.26
//        911 ：0.89
//        for (String key : userIdAndPercent.keySet()) {
//            System.out.println(key + " ：" + userIdAndPercent.get(key));
//        }
//__________________________-___________________________
        model.addAttribute("manager",manager);
        System.out.println("刮风这天我试过握着你手~~~ 你的ta为"+manager.getmName());
        return "showManager";
    }


//————————————————————————————————————————————————————————————————————————————————————————
//————————————————————————————————————————————————————————————————————————————————————————


    //得到Date型中的 小时数
    private static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }


//    List列表：【所有用户】高峰时段
    private List<Integer> allUserPeakHours(){

        //返回所有用户
        List<Behavior> behaviorList = behaviorService.selectAllBehavior();
//        每个时段的平均点击次数
        int av_point = behaviorList.size()/24;

        int [] con = new int[24];
        for (int i=0;i<24;i++){con[i]=0;}//初始化数组
//循环所有用户的每条行为记录 的小时数,循环完毕后，con1[]中的24个位置分别为所有用户 在该时间段点击次数；
        for (int k=0;k<behaviorList.size();k++){
            int m = getHour(behaviorList.get(k).getbTime());//得到小时数 getHour()
            con[m]++;
        }
        //把大于临界值av_point 的归入 所有用户的高峰时段，把高峰时段计入 List pu中
        List<Integer> pu = new ArrayList<Integer>();  //所有用户高峰时段 8、9、10、14、17、18、19、20、23
        for (int i=0;i<24;i++){
            if (con[i]>av_point){
                System.out.println(i+"  "+con[i]);
                pu.add(i);
            }
        }
        System.out.println("   所有用户的高峰时段共     "+pu.size()+"个  ，前两个为 "+pu.get(0)+"  "+pu.get(1));

        return pu ;
    }

//    List列表：【某个用户】高峰时段
    private List<Integer> oneUserPeakHours(String userId){
        //指定用户
        List<Behavior> behaviorList1= behaviorService.selectByUserId(userId);

        int av_point = behaviorService.selectAllBehavior().size()/24;
        int [] con1 = new int[24];
        for (int i=0;i<24;i++){con1[i]=0;}//初始化数组
//循环该用户的每条行为记录 的小时数,循环完毕后，con1[]中的24个位置分别为该用户 在该时间段点击次数；
        for (int k=0;k<behaviorList1.size();k++){
            int m1 = getHour(behaviorList1.get(k).getbTime());
            con1[m1]++;
            System.out.println("用户【"+userId+"】的第"+(k+1)+"条行为记录的小时数:"+m1+"，此时段点击量:"+con1[m1]);
        }
        //把大于临界值av_point 的归入该用户的高峰时段，把高峰时段计入 List pu1中
        List<Integer> pu1 = new ArrayList<Integer>();//某用户高峰时段：1、2、3、8、10、23    ,共6个
        System.out.println("*************************");
        for (int i=0;i<24;i++){
            if (con1[i]>av_point)
            {//System.out.println(i+"时的行为次数为 "+con1[i]);
                pu1.add(i);}
        }
        System.out.println("   用户【"+userId+"】共"+pu1.size()+"个高峰小时 ，行为次数都大于临界值"+av_point);
        return pu1 ;
    }


//    返回不同度(double) ，List列表：【所有】与【某个】的不同时段
    private static double returnPercent (List<Integer> pu,  List<Integer> pu1){

        List<Integer> pu2 = new ArrayList<Integer>();//不同的活跃小时的集合：1、2、3
        for (int i=0;i<pu1.size();i++){
            int state = 0 ;
            for (int j=0;j<pu.size();j++){
                if (pu1.get(i).equals(pu.get(j))){ //判断pu1 和pu之间是否有相同的值，如有，state变为999
                    state=999;
                    break;
                }
            }
            if (state==0){
                pu2.add(pu1.get(i));
            }
        }
        System.out.println("   不同的时段共有  "+pu2.size()+"个");

        int p = pu1.size();if (p==0)p=1;
        //该用户的不同度为：
        return (double) (pu2.size()/p);
    }

    //最终所有操作
    private void warnManage (){
        List<Behavior> behaviorList = behaviorService.selectAllBehavior();

        List<Integer> pu = allUserPeakHours();

        Map<String,Double> userIdAndPercent = new HashMap<String,Double>();



        for (int i=0;i<behaviorList.size();i++){
            String userId = behaviorList.get(i).getbUserId();
            List<Integer> pu1 = oneUserPeakHours(userId);
            double percent = returnPercent(pu,pu1);

            userIdAndPercent.put(userId,percent);                                              ////////////////////

            NumberFormat df = new DecimalFormat("#.00%");
            String percent1 =df.format(percent);    //percent1是String 值，用来输出百分比
            System.out.println("用户【"+userId+"】的不同度百分比为："+percent1);
        }
//排序！！

// 通过ArrayList构造函数把map.entrySet()转换成list
        List<Map.Entry<String, Double>> list = new ArrayList<Map.Entry<String, Double>>(userIdAndPercent.entrySet());
// 通过比较器实现比较排序
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> mapping1, Map.Entry<String, Double> mapping2) {
                return mapping1.getValue().compareTo(mapping2.getValue());
            }
        });
        int k = 1;
        //取前百分之十
        int qian_baifenzhi_10 = list.size()/10;
        System.out.println("**************************************\n最终排名~:");
        for (Map.Entry<String, Double> mapping : list) {

            System.out.println("第"+k+"名"+mapping.getKey() + " ：" + mapping.getValue());
            NumberFormat df = new DecimalFormat("#.00%");
            String percent1 =df.format(mapping.getValue());    //percent1是String 值，用来输出百分比

            //插入分析行为数据库
            BehaviorAna behaviorAna = new BehaviorAna();
            behaviorAna.setStandard(percent1);
            behaviorAna.setAnaTime(new Date());
            behaviorAna.setUserId(Integer.parseInt(mapping.getKey()));
            behaviorAnaService.insert(behaviorAna);


            if(k<qian_baifenzhi_10) {
                System.out.println("该用户预警！");
                User u = userService.selectByPrimaryKey(mapping.getKey());
                u.setIsWarn("1");
                userService.updateByPrimaryKey(u);
            }
            else {
                System.out.println("用户正常！");
                User u = userService.selectByPrimaryKey(mapping.getKey());
                u.setIsWarn("0");
                userService.updateByPrimaryKey(u);
            }
            k++;
        }
    }


}