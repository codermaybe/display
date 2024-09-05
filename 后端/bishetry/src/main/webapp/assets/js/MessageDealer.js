$(function(){

    //清除附件按钮
    $(document).on('click','#Refresh',function(){

        document.getElementById('ImgMsgHidden').value='';
        document.getElementById('FileMsgHidden').value='';
        document.getElementById('textMSg').value='';

    })


    //设置图片文件上传格式
    $(document).on('click','#ImgUpdate',function(){

        $("#ImgMsgHidden").click();

    })
    //设置文件选择button点击后开始
    $(document).on('click','#FileUpdate',function(){
        $("#FileMsgHidden").click();
    })

    $(document).on('click','#MsgSend',function(){
        //获取输入的文字
        document.getElementById('textMsgHidden').value = document.getElementById('textMSg').value;
        document.getElementById('textMSg').value = '';
        //提交表单给后端
        $('#UploadButton').click();
        //清空
        document.getElementById('Refresh').click();
    })


})
