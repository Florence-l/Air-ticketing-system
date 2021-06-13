var departurecity = unescape(getQueryString("book_departurecity"));
var arrivalcity = unescape(getQueryString("book_arrivalcity"));
var originalPrice = parseInt(unescape(getQueryString("book_price")));
var order_id = unescape(getQueryString("order_id"));
var order_num = unescape(getQueryString("order_num"));
document.getElementById('gdeparturecity').innerText = departurecity;
document.getElementById('garrivalcity').innerText = arrivalcity;

//查询按钮
getid=function (){
    document.getElementById("flightInquiry").submit();
    var c = document.getElementById("gdate").value;
    if(c.length==0){
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.msg('请输入查询日期');
        });
    }
    else if(isvalidate(c)==false){
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.msg('请输入正确格式的日期');
        });
    }
    else {
        window.location.href = 'http://localhost:8080/result?departurecity=' + departurecity + '&arrivalcity=' + arrivalcity + '&date=' + c;
    }
}

//判断日期格式
function isvalidate(str)
{
    var r = str.match(/^(\d{1,4})(.|\/)(\d{1,2})\2(\d{1,2})$/);
    if(r==null)return false;
    var d= new Date(r[1], r[3]-1, r[4]);
    if(r[3].length!=2||r[4].length!=2) return false;
    return (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]);
}


//改签功能
function change(){




    //获取change
    //如果补差价，change置为1
    //如果退差价，change置为2

    //加弹窗 提示补差价、退差价多少


    //将新的改签信息录入数据库
    $.ajax({
        type:'post',
        url:'/change',
        data:{
            change: change,
            order_id: order_id,
            order_num: order_num,
            passenger_id: passenger_id
        },
        success:function(res){
            if(res==="success"){
                if(change === "1"){
                    //跳转支付界面
                }else if(change === "2"){
                    //跳转退款界面
                }

            }
        }
    })

}