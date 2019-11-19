/**
 * 页面初始化后，绑定函数。
 */
$(function(){
	//注册
	$("#regist_button").click(function(){
		register();
	});
	
	//登录
	$("#login").click(function(){
        login();
	});
	
	//登出
	$("#logout").click(function(){
		logout();
	});
	
	//修改密码
	$("#changePassword").click(function(){
		changepwd();
	})
	
});
function checkName() {
    var name=$('#regist_username').val().trim();
    if(name==null|| name.length==0){
        $('#regist_username').attr("data-content","用户名不能为空");
        $('#regist_username').popover('show');
        return;
    }
    $.ajax({
        url:'/hehe/check.do',
        method:'post',
        data:{name:name},
        success:function (u) {
           if(!u){
               $('#regist_username').attr("data-content","用户名已存在");
               $('#regist_username').popover('show');
           }
        }
    })
}
//注册
function register() {
    var name=$('#regist_username').val().trim();
    var nickname=$('#nickname').val().trim();
    var password=$('#regist_password').val().trim();
    var finalPasword=$('#err_password').val().trim();
    if(name==null|| name.length==0){
        $('#regist_username').attr("data-content","用户名不能为空");
        $('#regist_username').popover('show');
        return;
    }
    if(nickname==null||nickname.length==0){
        $('#nickname').attr("data-content","昵称不能为空");
        $('#nickname').popover('show');
        return ;
    }
    if(password==null||password.length<6){
        $('#regist_password').attr("data-content","密码长度必须大于六位");
        $('#regist_password').popover('show');
        return ;
    }
    if(finalPasword==null||finalPasword.length<6){
        $('#err_password').attr("data-content","确认密码长度必须大于六位");
        $('#err_password').popover('show');
        return ;
    }
    if(password!=finalPasword){
        $('#err_password').attr("data-content","两次密码不一致");
        $('#err_password').popover('show');
        return ;
    }
    $.ajax({
        url:'/hehe/add.do',
        method:'post',
        data:{name:name,password:password,nickname:nickname},
        success:function (e) {
            if(e['success']){
                alert("注册成功");
                $("#zc").attr("class","sig sig_out");
                $("#dl").attr("class","log log_in");
            }else{
                alert(e['msg']);
            }
        }
    })
}
  function clearError(e) {
      $(e).popover('hide');
  }
//登陆
function login() {
	var name=$('#name').val().trim();
	var password=$('#password').val().trim();
	if(name==null|| name.length==0){
		$('#name').attr("data-content","用户名不能为空");
		$('#name').popover('show');
	   return;
	}
	if(password==null||password.length==0){
        $('#password').attr("data-content","密码不能为空");
        $('#password').popover('show');
       return ;
	}
	 $.ajax({
		 url:'/hehe/login.do',
         method:'post',
		 data:{name:name,password:password},
		 success:function (e) {
		 	if(e['success']){
		 		var user= e['value'];
		 		addCookie("userId",user.id);
		 		addCookie("username",user.name);
		 		addCookie("userpassword",user.password);
		 		addCookie("nickname",user.nickname);
		 		alert("登陆成功");
		 		location.href="edit.html";
			}else{
		 		alert(e['msg']);
                $("#zc").attr("class","sig sig_out");
                $("#dl").attr("class","log log_in");
			}
         }
	 })
}

/**
 * 退出登录
 */
function logout(){
    $.ajax({
        url:'/hehe/delete.do',
        method:'get',
        success:function (e) {
            if(e['success']){
                alert("退出成功.");
                setCookie("userId",'',-1);
                setCookie("username",'',-1);
                setCookie("userpassword",'',-1);
                setCookie("nickname",'',-1);
                $("#zc").attr("class","sig sig_out");
                $("#dl").attr("class","log log_in");
            }else{
                alert(e['msg']);
            }
        }
    })
}

/**
 * 修改密码
 */
function changepwd(){
     var lastpassword=$('#last_password').val().trim();
     var newpassword=$('#new_password').val().trim();
     var finalpassword=$('#final_password').val().trim();
    var userId=getCookie('userId');
    var userpassword=getCookie('userpassword');
    if(lastpassword==null|| lastpassword.length==0){
        $('#last_password').attr("data-content","原密码不能为空");
        $('#last_password').popover('show');
        return;
    }
    if(userpassword!=lastpassword){
        $('#last_password').attr("data-content","原密码输入错误");
        $('#last_password').popover('show');
        return;
    }
    if(newpassword==null||newpassword.length<6){
        $('#new_password').attr("data-content","新密码长度必须大于六位");
        $('#new_password').popover('show');
        return ;
    }
    if(finalpassword==null||finalpassword.length<6){
        $('#final_password').attr("data-content","再次输入密码长度必须大于六位");
        $('#final_password').popover('show');
        return ;
    }
    if(newpassword!=finalpassword){
        $('#err_password').attr("data-content","两次密码不一致");
        $('#err_password').popover('show');
        return ;
    }
    $.ajax({
        url:'/hehe/update.do',
        method:'post',
        data:{id:userId,password:newpassword},
        success:function (e) {
            if(e['success']){
                alert("修改成功.");
                logout();
            }else{
                alert(e['msg']);
            }
        }
    })
}


