<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form id="login_form">
	<div class = "login_tab">
		<div> 부서번호 </div>
		<div><input id="deptname" class="login_text" type="text" name="deptNo"/></div>
		<div>EMPNO</div>
		<div>
			<input id="username" class="login_text" type="text" name="empNo"/>
		</div>
			
		
		<div>ENAME </div>
		<div>
			<input id="userpw" class="login_text" type="text" name="ename"/>
		</div>
		
		
		<div>
			<input id="login_btn" type="button" value="로그인"/>
		</div>
		<div>
			<a id="a_join" href="#">회원가입</a>
		</div>
		<input type="hidden" name="action" value="login"/>
		<input type="hidden" name="page" value="mypage"/>
	</div>
</form>
<script>
app.init('${ctx}');
</script>
