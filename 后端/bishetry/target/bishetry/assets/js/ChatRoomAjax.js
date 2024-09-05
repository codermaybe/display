$(function(){

    //定时刷新列表
    window.setInterval(getFontFromServer,1000);
    window.setInterval(getChatFontFromServer,3000);

    //向服务器获取实时的用户列表
    function getFontFromServer(){

        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange=function(){
            if (xmlhttp.readyState==4 && xmlhttp.status==200)
            {
                $('#ChatList').html(xmlhttp.responseText);
            }
        }
        xmlhttp.open('get','http://localhost:8080/bishetry_war_exploded/ChatListFontServlet',true);
        xmlhttp.send();

    }

    function getChatFontFromServer(){
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange=function(){
            if(xmlhttp.readyState==4&&xmlhttp.status==200){
                $('#ChatRoom').html(xmlhttp.responseText);
            }
        }

        xmlhttp.open('POST','http://localhost:8080/bishetry_war_exploded/ChatConnectServlet',true);
        xmlhttp.send();

    }






});
