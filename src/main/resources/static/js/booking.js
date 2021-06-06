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
    for(var i=1;i<length;i++) {
        if (nameArray[i].value === "" || checkName(nameArray[i])==false){
            alert("请正确填写姓名字段！")
            return
        }else{
            if(idArray[i].value === "" || checkId(idArray[i])==false){
                alert("请正确填写长度为15或18的身份证字段！")
                return
            }else{
                if(telArray[i].value === "" || checkTel(telArray[i])==false){
                    alert("请正确填写长度为11的号码字段！")
                    return
                }
            }
        }
    }
    if(length<=5){
        $('.add-passenger').before($('#mycard').html());
    }else{
        alert("最多只能添加五个乘客！")
    }
}

        <!-- 点击“下一步”按钮 触发next()事件 -->
    function next(){

            //获取乘客信息表单并检查每个字段是否为空
            var nameArray = document.getElementsByName("user_name");
            var idArray = document.getElementsByName("passenger_id");
            var telArray = document.getElementsByName("user_tel");
            var telP = "未填写"
            if(document.getElementsByName("I_contact_phone").length != 0)  telP = document.getElementsByName("I_contact_phone");


            var length = nameArray.length;
            var nowDate = new Date();
            var orderTime = nowDate.getFullYear()+"."+(parseInt(nowDate.getMonth())+1)+"."+nowDate.getDate()
            +" "+nowDate.getHours()+":"+nowDate.getMinutes()+":"+nowDate.getSeconds();
            var price = unescape(getQueryString("book_price"));
            var realPrice = parseInt(price)+parseInt(50);
            var order_num = nowDate.getFullYear()+(parseInt(nowDate.getMonth())+1).toString()+nowDate.getDate()+nowDate.getHours()+nowDate.getMinutes()+nowDate.getSeconds();
            var flight_id = unescape(getQueryString("book_flight_id"));


    for(var i=1;i<length;i++) {
        if (nameArray[i].value === "" || checkName(nameArray[i])===false){
            alert("请正确填写姓名字段！")
            return
        }else{
            if(idArray[i].value === "" || checkId(idArray[i])===false){
                alert("请正确填写长度为15或18的身份证字段！")
                return
            }else{
                if(telArray[i].value === "" || checkTel(telArray[i])===false){
                    alert("请正确填写长度为11的号码字段！")
                    return
                }
            }
        }
    }

    var totalPrice=document.getElementById("J_totalPrice").innerHTML;
    var des=document.getElementById('d_city').innerHTML;
    var arrival=document.getElementById('a_city').innerHTML;
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


        for(var i=1; i<length; i++){
            $.ajax({
                type:'post',
                url:'/insertOrder',
                data:{
                    user_name: nameArray[i].value,
                    passenger_id: idArray[i].value,
                    flight_id: flight_id,
                    orderTime: orderTime,
                    paymentStatus: 0,
                    realPrice: realPrice,
                    order_num:order_num,
                },
                success:function(res){
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
                    order_num: order_num,
//                    I_contact_phone ：telP,

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



/*
<!-- 输入渐入渐出效果 -->
$(".txtb input").on("focus",function(){
    $(this).addClass("focus")
})

$(".txtb input").on("blur",function(){
    if($(this).val() == '')
        $(this).removeClass("focus")
})*/
