$(function() {

    $(document).on("click", "li[name='UserCard']", function() {
        $(this).siblings('li').removeClass('active'); // 删除其他兄弟元素的样式

        $(this).addClass('active'); // 添加当前元素的样式

        $("#InitPage").css('display', "none"); //生成页隐藏

        var name = $(this).find("h6[name='ChatCardName']").html(); //获取当前所指用户的姓名

        var id = $(this).attr('userid');

        var status = $(this).find("div[name='ChatCardInfo']").html(); //获取备注信息

        var icon = $(this).find("img[name='ChatCardIcon']").attr('src'); //获取头像链接

        $("#Chatname").html(name);
        $("#ChatInfo").html(status);
        $("#ChatIcon").attr('src', icon);
        $("#UserChatPage").css('display', "inline");

        EstablishConnection(name,id);
    });

    //选中用户后向服务器请求连接
    function EstablishConnection(name,id) {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                //申请对页面进行修改
                console.log("尝试链接建立")
            }
        }
        var servlet = "http://localhost:8080/bishetry_war_exploded/RemoteUserServlet?";
        var name = name;
        var id = id;
        var EstablishUrl =servlet+"name="+name+"&id="+id;

        xmlhttp.open('POST',EstablishUrl , true);
        xmlhttp.send();

    }

});