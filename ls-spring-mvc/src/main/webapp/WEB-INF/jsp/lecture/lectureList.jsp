<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<title>Language School Lectures</title>
<%@ include file="../common/head.jsp"%>
</head>
<body>
	<%@ include file="../common/header.jsp"%>

	<table class="table table-striped">
		<thead>
			<tr>
				<th>id</th>
				<th>dayTime</th>
				<th>classroomId</th>
				<th>topic</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${lectures}" var="lecture">
				<tr>
					<td><c:out value="${lecture.id}" /></td>
					<td><c:out value="${lecture.dayTime}" /></td>
					<td><c:out value="${lecture.classroomId}" /></td>
					<td><c:out value="${lecture.topic}" /></td>
					<td><my:a href="/lecture/view/${lecture.id}"
							class="btn btn-primary">view</my:a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<sec:authorize access="hasRole('ROLE_LECTURER')">
		<my:a href="/lecture/new/" class="btn btn-primary">New lecture</my:a>
	</sec:authorize>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>