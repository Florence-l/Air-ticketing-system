 function getQueryString(name) {
            var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
            var r = window.location.search.substr(1).match(reg);
            if (r != null) {
                return unescape(r[2]);
            }
            return null;
        }
 function test(){
    alert("test");
    }
 function init(){
        var date = (unescape(getQueryString("book_date"))).slice(5);
        var departurecity = unescape(getQueryString("book_departurecity"));
        var arrivalcity = unescape(getQueryString("book_arrivalcity"));
        var departuretime = (unescape(getQueryString("book_departuretime"))).slice(0,-3);
        var arrivaltime = (unescape(getQueryString("book_arrivaltime"))).slice(0,-3);
        var arrival_airport = unescape(getQueryString("book_arrival_airport"));
        var flight_id = unescape(getQueryString("book_flight_id"));
        var departure_airport = unescape(getQueryString("book_departure_airport"));
        var price = unescape(getQueryString("book_price"));
        var totalPrice = parseInt(price)+parseInt(50);
        document.getElementById('date').innerHTML = date;
        document.getElementById('d_city').innerHTML = departurecity;
        document.getElementById('a_city').innerHTML = arrivalcity;
        document.getElementById('d_time').innerHTML = departuretime;
        document.getElementById('a_time').innerHTML = arrivaltime;
        document.getElementById('d_port').innerHTML = departure_airport;
        document.getElementById('a_port').innerHTML = arrival_airport;
        document.getElementById('p_price').innerHTML = price;
        document.getElementById('J_totalPrice').innerHTML = totalPrice ;
}

<!-- 点击“添加乘客”按钮 触发add()事件 -->
function add(){
    var nameArray = document.getElementsByName("user_name");
    var idArray = document.getElementsByName("passenger_id");
    var telArray = document.getElementsByName("user_tel");
    var length = nameArray.length;
    var n = length;

    //记录乘客的舱位选择情况
    var allInput = document.getElementsByTagName("input"); //获得所有的input
    var loopTime = allInput.length; //获得数量
    var seatArray = new Array();//存储选项的值
    var count=0;
    for(i = 1;i < loopTime;i++){
        if(allInput[i].type == "radio")//只对radio进行检查
            if(allInput[i].checked==true){ //如果被选择
                seatArray[count]=allInput[i].value;//记录所选的值
                count++;
            }
    }

    //根据乘客信息计算实际票价
    var l = seatArray.length;
    var seatType = seatArray[l-1];
    var age = getAge(idArray[length-1].value);
    var price = unescape(getQueryString("book_price"));
    var real_price = priceCalculate(seatType, age, price);


    //获取已有的乘客信息表单并检查每个字段是否为空
    for(var i=1;i<length;i++) {
        if (nameArray[i].value === "" || checkName(nameArray[i])===false){
            layui.use('layer', function(){
                var layer = layui.layer;
                layer.msg('请正确填写姓名字段！');
            });
            return
        }else{
            if(idArray[i].value === "" || checkId(idArray[i])===false){
                layui.use('layer', function(){
                    var layer = layui.layer;
                    layer.msg('请正确填写长度为15或18的身份证字段！');
                });
                return
            }else if(idArray[i].value === idArray[i-1].value){
                layui.use('layer', function(){
                    var layer = layui.layer;
                    layer.msg('身份证字段重复！请检查后重新填写');
                });
                return
            }else{
                if(telArray[i].value === "" || checkTel(telArray[i])===false){
                    layui.use('layer', function(){
                        var layer = layui.layer;
                        layer.msg('请正确填写长度为11的号码字段！');
                    });
                    return
                }
            }
        }
    }

    if(length<=5){
        $('.add-passenger').before($('#mycard').html().replaceAll('seatType','seatType'+n));
    }else{
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.msg('最多只能添加五个乘客！');
        });
    }
}

<!-- 点击“下一步”按钮 触发next()事件 -->
function next(){
    //获取乘客信息表单并检查每个字段是否为空
    var nameArray = document.getElementsByName("user_name");
    var idArray = document.getElementsByName("passenger_id");
    var telArray = document.getElementsByName("user_tel");
    var length = nameArray.length;
    var totalPrice=document.getElementById("J_totalPrice").innerHTML;
    var des=document.getElementById('d_city').innerHTML;
    var arrival=document.getElementById('a_city').innerHTML;

    var nowDate = new Date();
    var orderTime = nowDate.getFullYear()+"."+(parseInt(nowDate.getMonth())+1)+"."+nowDate.getDate()
        +" "+nowDate.getHours()+":"+nowDate.getMinutes()+":"+nowDate.getSeconds();
    var realPrice = document.getElementsByClassName('realPrice');
    console.log(realPrice[0].innerText);
    console.log(realPrice[1].innerText);
    console.log(realPrice[2].innerText);
    var order_num = nowDate.getFullYear()+(parseInt(nowDate.getMonth())+1).toString()+nowDate.getDate()+nowDate.getHours()+nowDate.getMinutes()+nowDate.getSeconds();
    var flight_id = unescape(getQueryString("book_flight_id"));

    //记录乘客的舱位选择情况
    var allInput = document.getElementsByTagName("input"); //获得所有的input
    var loopTime = allInput.length; //获得数量
    var seatArray = new Array();//存储选项的值
    var count=0;
    for(i = 1;i < loopTime;i++){
        if(allInput[i].type == "radio")//只对radio进行检查
            if(allInput[i].checked==true){ //如果被选择
                seatArray[count++]=allInput[i].value;//记录所选的值
            }
    }

    //提交时重新验空 保证准确性
    for(var i=1;i<length;i++) {
        if (nameArray[i].value === "" || checkName(nameArray[i])===false){
            layui.use('layer', function(){
                var layer = layui.layer;
                layer.msg('请正确填写姓名字段！');
            });
            return
        }else{
            if(idArray[i].value === "" || checkId(idArray[i])===false){
                layui.use('layer', function(){
                    var layer = layui.layer;
                    layer.msg('请正确填写长度为15或18的身份证字段！');
                });
                return
            }else if(idArray[i].value === idArray[i-1].value){
                layui.use('layer', function(){
                    var layer = layui.layer;
                    layer.msg('身份证字段重复！请检查后重新填写');
                });
                return
            }else{
                if(telArray[i].value === "" || checkTel(telArray[i])===false){
                    layui.use('layer', function(){
                        var layer = layui.layer;
                        layer.msg('请正确填写长度为11的号码字段！');
                    });
                    return
                }
            }
        }
    }

    //将乘客信息录入数据库
    for(var i=1; i<length; i++){
        $.ajax({
            type:'post',
            url:'/book',
            data:{
                user_name: nameArray[i].value,
                passenger_id: idArray[i].value,
                user_tel: telArray[i].value
            },
            success:function(res){
                if(res==="success"){
                }
            }
        })
    }

    //将订单信息录入数据库
    for(var i=1; i<length; i++){
        $.ajax({
            type:'post',
            url:'/insertOrder',
            data:{
                user_name: nameArray[i].value,
                passenger_id: idArray[i].value,
                flight_id: flight_id,
                seat_type: seatArray[i-1],
                orderTime: orderTime,
                paymentStatus: 0,
                realPrice: realPrice[i].innerText,
                order_num:order_num,
            },
            success:function(res){
                alert("ok");
            }
        })
    }
    $.ajax({
        async : false,
        type:'post',
        url:'/pay',
        data:{
            totalPrice: totalPrice,
            subject: des+"-"+arrival,
            order_num: order_num
        },
        success: function(response) {
            var newPage = window.open("about:blank", "_self");
            newPage.document.write(response);
        }
    })
}

//用正则表达式验证姓名（中英文）格式
function checkName(name) {
    var str=$(name).val();
    var reg= /^(([a-zA-Z+\.?\·?a-zA-Z+]{2,30}$)|([\u4e00-\u9fa5+\·?\u4e00-\u9fa5+]{2,30}$))/;
    //检测姓名的格式是否匹配
    if(!reg.test(str)){
        return false;
    }else {
        return true;
    }
}

//用正则表达式验证身份证号格式
function checkId(id){
    var str=$(id).val();
    var reg = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X|x)$/;
    if(!reg.test(str)){
        return false;
    }else{
        return true;
    }
}

//用正则表达式验证手机号格式（验证130-139,150-159,180-189号码段的手机号码）
function checkTel(tel){
    var str=$(tel).val();
    var reg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
    if(!reg.test(str))
    {
        return false;
    }else{
        return true;
    }
}

//计算乘客年龄
function getAge(idCard){
    var yearBirth;
    var monthBirth;
    var dayBirth;
    //判断身份证号码长度
    if (idCard.length == 15) {
        var org_birthday = idCard.substring(6, 12);
        //获取出生年月日
        yearBirth = "19" + org_birthday.substring(0, 2);
        monthBirth = org_birthday.substring(2, 4);
        dayBirth = org_birthday.substring(4, 6);
    } else if (idCard.length == 18) {
        //获取出生年月日
        yearBirth = idCard.substring(6,10);
        monthBirth = idCard.substring(10,12);
        dayBirth = idCard.substring(12,14);
    }
    //获取当前年月日并计算年龄
    var myDate = new Date();
    var monthNow = myDate.getMonth() + 1;
    var dayNow = myDate.getDate();
    var age = myDate.getFullYear() - yearBirth;
    if(monthNow < monthBirth || (monthNow == monthBirth && dayNow < dayBirth)){
        age--;
    }
    return age;
}

 /**
  * 计算航班价格函数
  * @param seattype 舱位类型  商务舱 1 经济舱 2
  * @param age 年龄 判断是成人票、儿童票、婴儿票的哪一类型
  * @param price 原始折前价格
  * @return 折扣后的价格
  */
function priceCalculate(seatType, age, price){
     //根据舱位 经济舱6.7折
     if(seatType === "2") price*=0.67;
     //判断是否为儿童[2,12) 5折
     if(2<=age&&age<12) price*=0.5;
     //判断是否为婴儿(0,2) 1折
     if(0<age&&age<2) price*=0.1;

    var flyDate = (unescape(getQueryString("book_date")));//起飞时间，年月日格式日期
    flyDate=flyDate.replaceAll(".","/");//转换为yyyy/MM/dd格式
    var fly_date = new Date(flyDate);
    var book_date = new Date();//订票时间
    var diff = fly_date.getTime() - book_date.getTime();//获取两个日期对象相减，单位是毫秒
    var days = parseInt(diff / (1000 * 60 * 60 * 24)) + 1;

     var random=Math.random();
     if(days==0);//当天，原价
     else if(days>0&&days<=3){//0~3天
         price*=(0.9+(3-days)*0.01);
     }
     else if(days>3&&days<=7){//3~7天
         price*=(0.85+(7-days)*0.01);
     }
     else if (days>7&&days<=14){//7~14天
         price*=(0.75+(14-days)*0.01);
     }
     else if (days>14&&days<30){//7.0~7.5
         price*=(0.7+(random/20));
     }
     else if (days==30) {//一个月 7.0折
         price*=0.7;
     }
     else if(days>30&&days<60){//6.5~7.0
         price*=(0.65+(random/20));
     }
     else if(days>=60){//两个月
         price*=0.65;
     }
     price = Math.round(price);
     return price;
 }



//实时显示价格
function liveShow(e){
    //记录乘客的舱位选择情况
    var allInput = document.getElementsByTagName("input"); //获得所有的input
    var loopTime = allInput.length; //获得数量
    var seatArray = new Array();//存储选项的值
    var count=0;
    for(i = 1;i < loopTime;i++){
        if(allInput[i].type == "radio")//只对radio进行检查
            if(allInput[i].checked==true){ //如果被选择
                seatArray[count]=allInput[i].value;//记录所选的值
                count++;
            }
    }

    //根据乘客信息计算实际票价
    var nameArray = document.getElementsByName("user_name");
    var idArray = document.getElementsByName("passenger_id");
    var length = nameArray.length;
    var l = seatArray.length;
    var seatType = seatArray[l-1];
    var age = getAge(idArray[length-1].value);
    var price = unescape(getQueryString("book_price"));
    var real_price = priceCalculate(seatType, age, price);

    e.innerText = ''+real_price;
    var sumPrice = 0
    for (var p of document.getElementsByClassName('realPrice')){
        if(parseInt(p.innerText)>0)sumPrice+=(parseInt(p.innerText))
    }
    var extra = 50*(length-1);
    sumPrice += extra;
    $('#J_totalPrice').text(sumPrice)
}

