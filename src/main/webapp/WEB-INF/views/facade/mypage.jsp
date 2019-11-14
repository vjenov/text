<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../common/head.jsp"/>
입력하신 ID ${employee.empNo}   <br />
입력하신 PW ${employee.ename} <br />
입력하신 부서번호 ${employee.deptNo} <br />
입력하신 직업 ${employee.job} <br />
입력하신 mgr ${employee.mgr} <br />
입력하신 날짜 ${employee.hireDate} <br />
입력하신 연봉 ${employee.sal} <br />
입력하신 comm ${employee.comm} <br />
<jsp:include page="../common/foot.jsp"/>
<script>
app.init('${ctx}');
</script>