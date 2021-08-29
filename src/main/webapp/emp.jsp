<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加页面</title>
<!--easyui支持引入  -->
<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css"/>
<script type="text/javascript" src="easyui/jquery-1.9.1.js"></script>
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
/**********页面初始化数据**************/
$(function(){
	$('#win').window('close');  //展示详情页面关闭 
	$('#ffemp').hide();  //员工管理页面关闭 
	$('#ffdep').hide();  //部门管理页面关闭 
	
	//获取后台传递的页面初始数据
	$.getJSON('doinit_emp.do',function(map){
		var lswf=map.lswf;
		var lsdep=map.lsdep;
		//处理复选框
		for(var i=0;i<lswf.length;i++){
			var wf=lswf[i];
			$("#wf").append("&nbsp;&nbsp;&nbsp;"+"<input type='checkbox' id='wids' name='wids' value='"+wf.wid+"'>"+wf.wname)
		}
		//处理下拉列表
		$("#cc").combobox({
			data:lsdep,//从哪取值
			valueField:"depid",//值的名称ID
			textField:"depname",//实际要写入的值
			value:5,//默认选中
			panelHeight:88//设置下拉列表框高度
		});
	});
})
/**********页面初始化数据end**************/

/*************保存和修改**************/
$(function(){
	//添加--保存
	$("#btsave").click(function(){
		$.messager.progress();	// 显示进度条
		$('#ffemp').form('submit', {
			url: "save_emp.do",
			onSubmit: function(){
				var isValid = $(this).form('validate');
				if (!isValid){
					$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
				}
				return isValid;	// 返回false终止表单提交
			},
			success: function(code){//表单提交成功的函数
				if(code=="1"){
					$.messager.alert('提示','保存成功');    
					$('#dg').datagrid('reload');    // 重新载入当前页面数据 
					$('#ffemp').hide();  //员工管理页面关闭 
					 $('#hsdg').show();		//显示展示列表
					 $('#hsp').show();		//显示顶部P标签
				}else{
					$.messager.alert('提示','保存失败');    
				}
				$.messager.progress('close');	// 如果提交成功则隐藏进度条
			}
		});


		
	});
	//取消
	$("#btreset").click(function(){
		
		window.open('emp.jsp');
	});
	
	//修改
	$("#btupdate").click(function(){
		$('#ffemp').hide();  //员工管理页面隐藏
		$.messager.progress();	// 显示进度条
		$('#ffemp').form('submit', {
			url: "update_emp.do",
			onSubmit: function(){
				var isValid = $(this).form('validate');
				if (!isValid){
					$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
				}
				return isValid;	// 返回false终止表单提交
			},
			success: function(code){//表单提交成功的函数
				if(code=="1"){
					$.messager.alert('提示','修改成功');    
					$('#dg').datagrid('reload');    // 重新载入当前页面数据  
					 $('#hsdg').show();		//显示展示列表
					 $('#hsp').show();		//显示顶部P标签
				}else{
					$.messager.alert('提示','修改失败');    
				}
				$.messager.progress('close');	// 如果提交成功则隐藏进度条
			}
		});


		
	});
	
});
/*************保存和修改end**************/


/********************员工分页列表begin****************************/
$(function(){
	$('#dg').datagrid({    
	    url:'findPageAll_emp.do',    //要访问的路径
	    pagination:true,			//表格底部显示分页工具栏
	    pageSize:5,					//初始化页面记录数
	    pageNumber:1,				//初始化页面页码
	    pageList:[5,10,15,20],		//初始页面大小（每页几条数据）选择列表
	    striped:true,				//斑马线效果
	    columns:[[    
	        {field:'eid',title:'编号',width:200,align:'center'},    //field:每列展示的字段名，ezui会自动将此字段的值填入表格
	        {field:'ename',title:'姓名',width:200,align:'center'},
	        {field:'sex',title:'性别',width:200,align:'center'},
	        {field:'address',title:'地址',width:200,align:'center'},
	        {field:'sdate',title:'生日',width:200,align:'center'},
	        {field:'phopo',title:'照片',width:252,align:'center',
	        	formatter: function(value,row,index){//row代表行记录
	        		return '<a href=uppic/'+row.phopo+'><img src=uppic/'+row.phopo+' width="100" height="60" /></a>'
	        	}
	        },
	        {field:'opt',title:'操作',width:252,align:'center',
	        	formatter: function(value,row,index){//row代表行记录
	        		var bt1="<input type='button' onclick=delByid("+row.eid+") value='删除'/>"
	        		var bt2="<input type='button' onclick=updByid("+row.eid+") value='编辑'/>"
	        		var bt3="<input type='button' onclick=findByid("+row.eid+") value='详情'/>"
	        		return bt1+bt2+bt3
	        	}
	        }
	        
	    ]]    
	});  

	
});
/********************员工分页列表end****************************/

/**********删除--编辑(查询单个)--详情**************/
 //删除
function delByid(eid){
	alert(eid+"删除");
	$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
	    if (r){    //如果确定
	        $.getJSON('delByid_emp.do?eid='+eid,function(code){
	        	if(code=="1"){
					$.messager.alert('提示','删除成功');    
					$('#dg').datagrid('reload');    // 重新载入当前页面数据  

				}else{
					$.messager.alert('提示','删除失败');    
				}
	        });   
	    }    
	});  

}
 //编辑
function updByid(eid){
	alert(eid+"编辑(查单)");
	 $('#hsdg').hide();		//隐藏展示列表
	 $('#hsp').hide();		//隐藏顶部P标签
	 $('#ffemp').show();  //员工管理页面显示 
	 $('#btupdate').show();//修改按钮显示
	 $('#btsave').hide();//保存按钮隐藏
	$.getJSON('findByid_emp.do?eid='+eid,function(oldemp){
		//先清空员工管理页面(ffemp)的数据（福利复选框）
		$(":checkbox[name='wids']").each(function(){//循环
			$(this).prop('checked',false);   //修改属性，不选中
		});
		//将后台拿到的数据写入到指定表单中
		$('#ffemp').form('load',{
			'eid':oldemp.eid,
			'ename':oldemp.ename,
			'sex':oldemp.sex,
			'address':oldemp.address,
			'sdate':oldemp.sdate,
			'depid':oldemp.depid,
			'emoney':oldemp.emoney
			
		});
			//处理图片
			$("#myphoto").attr('src','uppic/'+oldemp.phopo)//修改属性
			//处理复选框
			var wids=oldemp.wids;
			$(":checkbox[name='wids']").each(function(){//循环控件
				for(var i=0;i<wids.length;i++){//循环福利
					if($(this).val()==wids[i]){//判断列表的福利value值(也就是id)和查到的福利id是否相等 
						$(this).prop('checked',true);   //修改属性，选中
					}
				}
			});
	});
	 
}
 //详情
function findByid(eid){
	alert(eid+"详情(查单)");
	$.getJSON('findddByid_emp.do?eid='+eid,function(oldemp){
		//赋值
		$("#eidtext").html(oldemp.eid);
		$("#enametext").html(oldemp.ename);
		$("#sextext").html(oldemp.sex);
		$("#addresstext").html(oldemp.address);
		$("#sdatetext").html(oldemp.sdate);
		$("#phototext").html(oldemp.phopo);
		$("#deptext").html(oldemp.depname);
		$("#emoneytext").html(oldemp.emoney);
		//获取福利
		 var lswf=oldemp.lswf;//福利对象
		var wnames=[]//福利名称数组
		for(var i=0;i<lswf.length;i++){
			var wf=lswf[i];
			wnames.push(wf.wname);//push往数组里存值
		}
		var strwnames=wnames.join(',');//用逗号隔开的字符串
		$("#wftext").html(strwnames); 
		//处理展示的图片名称
		$("#domyphoto").attr('src','uppic/'+oldemp.phopo);
		//处理展示的图片链接名称
		$("#dodomyphoto").attr('href','uppic/'+oldemp.phopo);
		
		$('#win').window('open');  // 打开详情页面 
	});
}

 
/**********删除--编辑(查询单个)--详情end**************/
/*******按钮隐藏展示********/
 function glbt(){
	 alert("确认添加？");
	 $('#ffemp').show();  //员工管理页面显示 
	 $('#btupdate').hide();//修改按钮隐藏
	 $('#hsdg').hide();		//隐藏展示列表
	 $('#hsp').hide();		//隐藏顶部P标签
 }
/*******按钮隐藏展示end********/

/*******部门管理begin********/
function depmgbt(){
	alert("222")
	 $('#hsdg').hide();		//隐藏展示列表
	 $('#hsp').hide();		//隐藏顶部P标签
	$.getJSON('finddep_emp.do',function(lsdep){
		var tablehead=
			"<table id='deptable' align='center' width='300px' border='1px'>"
			+"<tr bgcolor='#FFCCCC' align='center'>"
			    +"<td colspan='3'>部门列表</td>"
		    +"</tr>"
			+"<tr align='center'>"
				+"<td>部门编号</td>"
				+"<td>部门名称</td>"
				+"<td>操作</td>"
			+"</tr>"
			 var trs='';
			for(var i=0;i<lsdep.length;i++){
				var dep=lsdep[i];
				trs+="<tr align='center'>"
				+"<td>"+dep.depid+"</td>"
				+"<td>"+dep.depname+"</td>"
			    +"<td> <input type='button' onclick=depdelete("+dep.depid+") value='删除'><input type='button' onclick=depfindByd("+dep.depid+") value='编辑'></td>" 
			+"</tr>"
			} 
			var a=
				"<tr bgcolor='#FFCCCC' align='center'>"
			    +"<td colspan='3'><input type='button' onclick=depsava() value='添加'></td>"
		        +"</tr>"
			var endtable=tablehead+trs+a+"</table>";
			$("#mydeptable").html(endtable);
			
	});
	
}

//部门添加
$(function(){
	$("#btdepsave").click(function(){
		alert("111")
		$.messager.progress();	// 显示进度条
		$('#ffdep').form('submit', {
			url: "savedep_emp.do",
			onSubmit: function(){
				var isValid = $(this).form('validate');
				if (!isValid){
					$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
				}
				return isValid;	// 返回false终止表单提交
			},
			success: function(code){//表单提交成功的函数
				if(code=="1"){
					$.messager.alert('提示','保存成功');    
					$('#dg').datagrid('reload');    // 重新载入当前页面数据 
					//window.open('emp.jsp');  //返回首页面
					
				}else{
					$.messager.alert('提示','保存失败');    
				}
				$.messager.progress('close');	// 如果提交成功则隐藏进度条
			}
		});
		
		

	});
//部门修改
	$(function(){
		$("#btttupdate").click(function(){
			alert("确认修改？")
			$.messager.progress();	// 显示进度条
			$('#ffdep').form('submit', {
			 url: "updatedep_emp.do",
			onSubmit: function(){
				var isValid = $(this).form('validate');
				if (!isValid){
					$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
				}
				return isValid;	// 返回false终止表单提交
			}, 
			success: function(code){//表单提交成功的函数
				if(code=="1"){
					$.messager.alert('提示','部门修改成功');  
					$('#ffdep').hide();  //部门管理页面隐藏
					$('#dg').datagrid('reload');    // 重新载入当前页面数据 
					 $('#hsdg').show();		//展示展示列表
					 $('#hsp').show();		//展示顶部P标签
					
				}else{
					$.messager.alert('提示','部门修改失败');    
				}
				$.messager.progress('close');	// 如果提交成功则隐藏进度条
			}
		
	});
	});
	});
	
	//取消
	$("#btttreset").click(function(){
		alert("111")
		window.open('emp.jsp');
	});
});

//点击添加隐藏开启表单
function depsava(){
	alert("111")
	$('#btdepsave').show();//保存按钮开启
	$('#ffdep').show();  //部门管理页面开启 
	$('#btttupdate').hide();//修改按钮隐藏
	$('#deptable').hide();  //部门展示页面关闭 
}

//部门编辑
function depfindByd(depid){
	alert("111"+depid)
	$('#ffdep').show();  //部门管理页面开启 
	$('#deptable').hide();//部门展示关闭
	$('#btdepsave').hide();//保存按钮隐藏
	$('#btttupdate').show();//修改按钮开启
	$.getJSON('findbyiddep_dep.do?depid='+depid,function(olddep){
		$('#ffdep').form('load',{
			'depid':olddep.depid,
			'depname':olddep.depname
			
		});
	});
}




/*******员工管理end********/
</script>
</head>

<body>
<p id="hsp" align="center" style="color:red "  >
<input type="button" id="glbt" name="glbt" value="添加" onclick=glbt()>
<input type="button" id="depmgbt" name="depmgbt" value="部门管理" onclick=depmgbt()>
员工列表
</p>

<!-- 表格 -->
<div id="hsdg">
<hr />
<table id="dg" ></table> 
<hr />
</div>

<form action="" name="ffemp" id="ffemp" method="post" enctype="multipart/form-data">
	<table border="1px" width="650px" align="center">
		<tr align="center" bgcolor="#FFFF33">
			<td colspan="3">员工管理</td>
		</tr>
		<tr>
			<td>姓名</td>
			<td>
				<input type="text" name="ename" id="ename" class="easyui-validatebox" data-options="required:true">
			</td>
			<td rowspan="7" align="center" width="300px">
				<a href="uppic/default.jpg">
					<img id="myphoto" alt="图片不存在" src="uppic/default.jpg" width="300px" height="250px">
				</a>
			</td>
		</tr>
		<tr>
			<td>性别</td>
			<td>
				<input type="radio" name="sex" id="sex" value="男" checked="checked">男
				<input type="radio" name="sex" id="sex" value="女" >女
			</td>
			
		</tr>
		<tr>
			<td>地址</td>
			<td>
				<input type="text" id="address" name="address">
			</td>
			
		</tr>
		<tr>
			<td>生日</td>
			<td>
				<input type="date" id="sdate" name="sdate">
			</td>
			
		</tr>
		<tr>
			<td>照片</td>
			<td>
				<input type="file" id="pic" name="pic">
			</td>
			
		</tr>
		<tr>
			<td>部门</td>
			<td>
				<input type="text" id="cc" name="depid">
			</td>
			
		</tr>
		<tr>
			<td>薪资</td>
			<td>
				<input type="text" id="emoney" name="emoney" value="3000">
			</td>
			
		</tr>
		<tr>
			<td>福利</td>
			<td colspan="3">
				<span id="wf" ></span>
			</td>
			
		</tr>
		<tr align="center" bgcolor="#FFFF33">
			<td colspan="3">
				<input type="hidden" name="eid" id="eid">
				<input value="保存" type="button" name="btsave" id="btsave">
				<input value="修改" type="button" name="btupdate" id="btupdate">
				<input value="取消" type="reset" name="btreset" id="btreset">
			</td>
		</tr>
	</table>
</form>
<!-- 部门展示表格 -->
<div id="mydeptable"></div>
<!-- 部门管理 -->
<form action="" name="ffdep" id="ffdep">
	<table	border="1px" width="400px" align="center">
		<tr align="center" bgcolor="#FFFF33">
			<td colspan="2">部门管理</td>
		</tr>
		<tr>
			<td>部门名称</td>
			<td>
				<input type="text" id="depname" name="depname">
			</td>
		</tr>
		<tr align="center" bgcolor="#FFFF33">
			<td colspan="2">
			<input type="hidden" id="depid" name="depid">
				<input value="保存" type="button" name="btdepsave" id="btdepsave">
				<input value="修改" type="button" name="btttupdate" id="btttupdate">
				<input value="取消" type="reset" name="btttreset" id="btttreset">
			</td>
		</tr>
	</table>
</form>
<!-- 详情窗口 -->
<div id="win" class="easyui-window" title="员工详情" style="width:600px;height:400px"   
        data-options="iconCls:'icon-save',modal:true"> 
   	<table border="1px" width="650px" align="center">
		<tr>
			<td width="80px" align="center">编号</td>
			<td width="220px">
				<span id="eidtext" ></span>
			</td>
			<td rowspan="8" align="center" width="300px">
				<a id="dodomyphoto" href="uppic/default.jpg">
					<img id="domyphoto" alt="图片不存在" src="uppic/default.jpg" width="300px" height="250px">
				</a>
			</td>
		</tr>
		<tr>
			<td align="center">姓名</td>
			<td>
				<span id="enametext" name="enametext"></span>
			</td>
			
		</tr>
		<tr>
			<td align="center">性别</td>
			<td>
				<span id="sextext"></span>
			</td>
			
		</tr>
		<tr>
			<td align="center">地址</td>
			<td>
				<span id="addresstext"></span>
			</td>
			
		</tr>
		<tr>
			<td align="center">生日</td>
			<td>
				<span id="sdatetext"></span>
			</td>
			
		</tr>
		<tr>
			<td align="center">照片</td>
			<td>
				<span id="phototext"></span>
			</td>
			
		</tr>
		<tr>
			<td align="center">部门</td>
			<td>
				<span id="deptext"></span>
			</td>
			
		</tr>
		<tr>
			<td align="center">薪资</td>
			<td>
				<span id="emoneytext"></span>
			</td>
			
		</tr>
		<tr>
			<td align="center">福利</td>
			<td colspan="3">
				<span id="wftext" ></span>
			</td>
			
		</tr>
		
	</table>  
</div>  

</body>
</html>