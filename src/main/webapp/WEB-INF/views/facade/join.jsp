<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form id="join_form">
	<div class="join_tab">
	<div><h3 id="join">회원가입</h3></div> <div></div>
	<div>empNo</div>
	<div>
		<input id="join_empNo" type="text" name="empNo"/><br />
	</div>
	<div>ename</div>
	<div>
		<input id="join_ename" type="text" name="ename"/><br />
	</div>
	<div>deptNo</div>
	<div>
		<input id="join_deptNo" type="text" name="deptNo"/><br />
	</div>
	<div>job</div>
	<div>
		<input type="text"name="job" /><br />
	</div>
	<div>mgr</div>
	<div>
		<input type="text" name="mgr"/><br />
	</div>
	<div>hireDate</div>
	<div>
		<input type="text" name="hireDate"/><br />
	</div>
	<div>sal</div>
	<div>
		<input type="text" name="sal"/><br />
	</div>
	<div>comm</div>
	<div>
		<input type="text" name="comm"/><br />
	</div>
	<input type="hidden" name="action" value="create"/>
	<input type="hidden" name="page"value="login"/>
	<input id="join_btn" type="button" value="가입하기" />
	</div>
</form>
<script>
app.init('${ctx}');
</script>