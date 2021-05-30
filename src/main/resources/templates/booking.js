<!-- 点击“添加乘客”按钮 触发add()事件 -->
function add(){
    var nameArray = document.getElementsByName("user_name");
    var idArray = document.getElementsByName("passenger_id");
    var telArray = document.getElementsByName("user_tel");
    var length = nameArray.length;
    for(var i=1;i<length;i++) {
        if (nameArray[i].value === ""){
            alert("请填写姓名字段！")
            return
        }else{
            if(idArray[i].value === ""){
                alert("请填身份证字段！")
                return
            }else{
                if(telArray[i].value === ""){
                    alert("请填写号码字段！")
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
        if (nameArray[i].value === ""){
            alert("请填写姓名字段！")
            return
        }else{
            if(idArray[i].value === ""){
                alert("请填身份证字段！")
                return
            }else{
                if(telArray[i].value === ""){
                    alert("请填写号码字段！")
                    return
                }
            }
        }
    }

    var p = [];
    for(var i=1;i<length;i++){
        p.push({
            0:nameArray[i].value,
            1:nameArray[i].value,
            2:nameArray[i].value
        })
    }
    console.log(p);


    //录入数据库
    $.ajax({
        type:'post',
        url:'/book',
        //traditional:true,
        data:{"array":p},
        success:function(res){
            if(res=="success"){
                setTimeout(function(){
                    //window.location.href='http://localhost:8080/payment';
                },1500);
            }
        }
    })




    /*$.ajax({
        type:'post',
        url:'/book',
        data:{
            'user_name':user_name,
            'passenger_id': passenger_id,
            'user_tel':user_tel
        },
        success:function(res){
            layer.msg('乘客添加成功！',{time: 1500,icon:6})
            if(res=="success"){
                alert('乘客添加成功！')
                //layer.msg('乘客添加成功！',{icon:6})
                setTimeout(function(){
                    window.location.href='http://localhost:8080/payment';
                },1500);
            }else{
                alert('乘客信息已存在~')
                //layer.msg('乘客信息添加失败！',{icon:5})
            }
        }
    })*/


    /*            $("#submitAdd").click(function(){
                    //var targetUrl = $("#addForm").attr("action");
                    var data = $("#addForm").serialize();
                    $.ajax({
                        type:'post',
                        url:'http://localhost:8080/book',
                        cache: false,
                        data:data,  //重点必须为一个变量如：data
                        dataType:'json',
                        success:function(data){
                            alert('success');
                            window.location.href='http://localhost:8080/payment';
                        },
                        error:function(){
                            alert("请求失败")
                        }
                    })
                })*/

}
