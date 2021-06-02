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
    var length = nameArray.length;

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
                    setTimeout(function(){
                        window.location.href='http://localhost:8080/payment';
                    },1500);
                }
            }
        })
    }
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
