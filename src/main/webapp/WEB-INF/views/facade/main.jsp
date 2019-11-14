<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="e" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../common/head.jsp"/>
<div>
	<h1 id="main_text">개좋냥</h1>
	<e:choose>
		<e:when test="${page eq 'login'}">
			<jsp:include page="login.jsp"/>
		</e:when>
		<e:when test="${page eq 'join'}">
			<jsp:include page="join.jsp"/>
		</e:when>
		<e:when test="${page eq 'mypage'}">
			<jsp:include page="mypage.jsp"/>
		</e:when>
	</e:choose>
</div>
<script>
app.init('${ctx}');
</script>
<jsp:include page="../common/foot.jsp"/>