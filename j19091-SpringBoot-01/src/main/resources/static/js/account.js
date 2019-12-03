$(document).ready(function() {
	/**
	 * 用户登录
	 */
	$("#loginButton").bind("click", function() {
		var user = {};
		user.userName = $("[name='userName']").val();
		user.password = $("[name='password']").val();
		$.ajax({
			url : "/account/doLogin",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(user),
			success : function(data) {
				if (data.status == 200) {
					location.href = "/account/dashboard";
				} else {
					$("#message").html("<span style='color:red'>"+data.message+"</span>");
				}
			},
			error : function(data) {
				$("#message").html("<span style='color:red'>"+data.message+"</span>");
			}
		});
	});
//	+++++++++++++++++++++++++++++
	/**
	 * 验证注册账号
	 */
	$("#userName").bind("blur", function() {
		var user={};
		 var account=$("#userName").val();
		 var uPattern = /^[a-zA-Z0-9]{4,8}$/;
		 if (uPattern.test(account)) {
			 user.userName=account;
			 $.ajax({
					url : "/account/checkUserName",
					type : "post",
					contentType : "application/json",//提交到后端的为序列化的json字符串
					data : JSON.stringify(user),  //json对象转化成json串
					success : function(data) {
						if (data.status == 200) {
							$("#registerButton").attr("disabled","disabled");
							$("#info").removeClass("alert-danger");
							$("#info").addClass("alert-success");
							$("#info").css("display","block");
							$("#message").html("<span>"+data.message+"</span>");
						} else {
							$("#registerButton").attr("disabled","disabled");
							$("#info").removeClass("alert-success");
							$("#info").addClass("alert-danger");
							$("#info").css("display","block");
							$("#message").html("<span>"+data.message+"</span>");
						}
					},
					error : function(data) {
						/*$("#message").html("<span style='color:red'>"+data.message+"</span>");*/
					}
				});
		} else {
			$("#registerButton").attr("disabled","disabled");
			$("#info").removeClass("alert-success");
			$("#info").addClass("alert-danger");
			$("#info").css("display","block");
			$("#message").html("<span>用户名格式不正确</span>");
		}
		
	});
//	+++++++++++++++++++++++++
	/**
	 * 检验密码长度
	 */
	$("#password").bind("keyup", function() {
		var password=$("#password").val();
		var uPattern = /^[a-zA-Z0-9_-]{6,10}$/;
		if (uPattern.test(password)) {
			$("#info").removeClass("alert-danger");
			$("#info").addClass("alert-success");
			$("#info").css("display","block");
			$("#message").html("<span>密码格式正确</span>");
			$("#registerButton").removeAttr("disabled");
		} else {
			$("#registerButton").attr("disabled","disabled");
			$("#info").removeClass("alert-success");
			$("#info").addClass("alert-danger");
			$("#info").css("display","block");
			$("#message").html("<span>密码格式不正确</span>");
		}
	});
	
//	++++++++++++++++++
	/**
	 * 注册用户
	 */
	$("#registerButton").bind("click", function() {
		var user = {};
		user.userName = $("[name='userName']").val();
		user.password = $("[name='password']").val();
		$.ajax({
			url : "/account/register",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(user),
			success : function(data) {
				if (data.status == 200) {
					alert(data.message);
					location.href = "/account/login";
				} else {
					$("#message").html("<span style='color:red'>"+data.message+"</span>");
				}
			},
			error : function(data) {
				$("#message").html("<span style='color:red'>"+data.message+"</span>");
			}
		});
	});
	
	
//	++++++++++++++++++++++++++++++++++++
	/**
	 * 加载用户的信息
	 */
	$("#userList [name='userEdit']").bind("click", function() {
		$("#userList").hide();
		$("#userEdit").show();
		/*获取点击行的用户名和用户ID*/
		var userName=$(this).parents("tr").find("[name='userName']").text();
		var id=$(this).parents("tr").find("[name='userId']").text();
		$("#userName").val(userName);
		$("#userId").val(id);
		$.ajax({
			url : "/account/getRolesByUserId?userId="+id,
			type : "get",
			contentType : "application/json",
			success : function(data) {
				$.each(data,function(i,val){
					$("input[name='roleCheckbox'][value="+val.roleId+"]").attr("checked","checked");
				});
			},
			error : function(data) {
				/*$("#message").html("<span style='color:red'>"+data.message+"</span>");*/
			}
		});
	});
	
//	+++++++++++++++++++++++++++++
	/**
	 * 提交修改的内容
	 */
	$("#userSubmit").bind("click", function() {
		var user={};
		user.userId=$("#userId").val();
		user.userName=$("#userName").val();
		//准备一个数组，用于装选中的角色
		var roles=new Array();
		$.each($("input[name='roleCheckbox']"),function(){
			if(this.checked==true){
				var role={};
				role.roleId=$(this).val();
				roles.push(role);
			}
		});
		user.roles=roles;
		$.ajax({
			url:"/account/updateUserRole",
			type:"POST",
			contentType : "application/json",
			data : JSON.stringify(user),
			success : function(data) {
				if (data.status == 200) {
					$("[name=message]").text(data.message);
					$("[name=messageDiv]").show();
					/*location.href = "/account/users";*/
				} else {
					$(".alert").removeClass("alert-info").addClass("alert-danger");	
					$("[name=message]").text(data.message);
					$("[name=messageDiv]").show();
				}
			},
			error : function(data) {
				$(".alert").removeClass("alert-info").addClass("alert-danger");	
				$("[name=message]").text(data.message);
				$("[name=messageDiv]").show();
			}
			
		});
		
	});
	
	/*++++++++++++++++++++++++++以下为roles部分的js代码+++++++++++++++++++++++*/
	/**添加角色*/
	$("#addRole").bind("click",function(){
		var role={};
		role.roleName=$("[name='addRoleName']").val();
		$.ajax({
			url : "/account/addRole",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(role),
			success : function(data) {
				if (data.status == 200) {
					alert(data.message);
					location.href = "/account/roles";
				} else {
					alert(data.message);
				}
			},
			error : function(data) {
				alert(data.message);
			}
		});
	});
	
	//+++++++++++++++++++++++++++++++++
	/**
	 * 在修改角色页面，加载原先的角色信息
	 */
	$("[name='editRole']").bind("click",function(){
		$("#roleList").hide();
		$("#roleEdit").show();
		var roleId=$(this).parents("tr").find("[name='roleId']").text();
		var roleName=$(this).parents("tr").find("[name='roleName']").text();
		$("#roleId").val(roleId);
		$("#roleName").val(roleName);
		
	});
	
//	++++++++++++++++++++++++++++++++
	/**
	 * 实现修改角色
	 */
	$("#roleSubmit").bind("click",function(){
		var role={};
		role.roleId=$("#roleId").val();
		role.roleName=$("#roleName").val();
		$.ajax({
			url : "/account/updateRole",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(role),
			success : function(data) {
				if (data.status == 200) {
					$("[name=message]").text(data.message);
					$("[name=messageDiv]").show();
//					location.href = "/account/roles";
				} else {
					$(".alert").removeClass("alert-info").addClass("alert-danger");	
					$("[name=message]").text(data.message);
					$("[name=messageDiv]").show();
				}
			},
			error : function(data) {
				$(".alert").removeClass("alert-info").addClass("alert-danger");	
				$("[name=message]").text(data.message);
				$("[name=messageDiv]").show();
			}
		});
	});
	
//	++++++++++++++++++++resource部分+++++++++++++++++++++++
	$("#addResource").bind("click",function(){
		var resource={};
		resource.resourceName=$("#addResourceName").val();
		resource.resourceUri=$("#addResourceUri").val();
		resource.permission=$("#addPermission").val();
		$.ajax({
			url : "/account/addResource",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(resource),
			success : function(data) {
				if (data.status == 200) {
					alert(data.message);
					location.href = "/account/resources";
				} else {
					alert(data.message);
				}
			},
			error : function(data) {
				alert(data.message);
			}
		});
	});
//	+++++++++++++++++++++++++++++++
		/**
		 * 实现加载原来的资源信息
		 */
	$("[name='editResource']").bind("click",function(){
		var resourceId= $(this).parents("tr").find("[name='resourceId']").text();
		var resourceName= $(this).parents("tr").find("[name='resourceName']").text();
		var resourceUri= $(this).parents("tr").find("[name='resourceUri']").text();
		var permission= $(this).parents("tr").find("[name='permission']").text();
		$("#resourceId").val(resourceId);
		$("#resourceName").val(resourceName);
		$("#resourceUri").val(resourceUri);
		$("#permission").val(permission);
		//得到拥有该资源的角色
		$.ajax({
			url : "/account/getRolesByResourceId?resourceId="+resourceId,
			type : "get",
			contentType : "application/json",
			/*data : JSON.stringify(resource),*/
			success : function(data) {
				$.each(data,function(i,val){
					$("input[name='roleCheckbox'][value="+val.roleId+"]").attr("checked","checked");
				});
			},
			error : function(data) {
				/*alert(data.message);*/
			}
		});
		$("#resourceList").hide();
		$("#resourceEdit").show();
		
	}); 
	
//	+++++++++++++++++++++++++++++++
	/**
	 * 实现修改资源信息
	 */
	$("#resourceSubmit").bind("click",function(){
		var resource={};
		resource.resourceId=$("#resourceId").val();
		resource.resourceName=$("#resourceName").val();
		resource.resourceUri=$("#resourceUri").val();
		resource.permission=$("#permission").val();
		var roles=new Array();
		$.each($("input[name=roleCheckbox]"),function(){
			if(this.checked){
				role={};
				role.roleId=$(this).val();
				roles.push(role);
			}
		});
		resource.roles=roles;
		//修改资源
		$.ajax({
			url : "/account/updateResource",
			type : "post",
			contentType : "application/json",
			data : JSON.stringify(resource),
			success : function(data) {
				if (data.status == 200) {
					$("[name=message]").text(data.message);
					$("[name=messageDiv]").show();
				} else {
					$("[name=message]").text(data.message);
					$("[name=messageDiv]").show();
				}
			},
			error : function(data) {
				$("[name=message]").text(data.message);
				$("[name=messageDiv]").show();
			}
		});
		
		
	});
});



