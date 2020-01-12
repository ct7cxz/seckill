function login(){
    $('#loginForm').validate({
        submitHandler:function(form){
            doLogin();
        }
    });
}
function doLogin(){
    g_showLoading();
    var pass = md5(g_passsword_salt.charAt(2)+g_passsword_salt.charAt(1)+$("#password").val()+g_passsword_salt.charAt(5)+g_passsword_salt.charAt(0))
    console.log(pass)
    $.ajax({
        type : "POST",
        url :  "/seckill/login/doLogin",
        data : {
            "mobile":$("#mobile").val(),
            "password":pass
        },
        success : function(data){
            layer.closeAll();
            if(data.code == 0){
                layer.msg("登录成功",{time: 1000},function(){
                    window.location.href="/goods/to_list";
                });
            }else{
                alert(data.msg);
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            layer.closeAll();
            alert("异常");
        }
    });
}