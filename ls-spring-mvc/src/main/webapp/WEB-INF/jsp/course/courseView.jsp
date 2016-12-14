<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<title>Language School Course</title>
<%@ include file="../common/head.jsp"%>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<table class="table">
		<thead>
			<tr>
				<th>id</th>
				<th>name</th>
				<th>language</th>
				<th>proficiency level</th>
				<th>action</th>
			</tr>
		</thead>

		<tbody>
			<tr>
				<td>${course.id}</td>
				<td><c:out value="${course.name}" /></td>
				<td><c:out value="${course.language}" /></td>
				<td><c:out value="${course.proficiencyLevel}" /></td>
				<td>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<a
						href="${pageContext.request.contextPath}/course/edit/${course.id}"
						class="btn btn-primary">Edit</a>
					<a
						href="${pageContext.request.contextPath}/course/delete/${course.id}"
						class="btn btn-primary">Delete</a>
				</sec:authorize> <sec:authorize access="hasRole('ROLE_STUDENT')">
					<a
						href="{pageContext.request.contextPath}/course/enrollToCourse/${course.id}"
						class="btn btn-primary">Enroll to Course</a>
				</sec:authorize></td>
			</tr>
		</tbody>
	</table>

	Lectures in Course:
	<table class="table table-striped">
		<tbody>
			<c:forEach items="${lecturesInCourse}" var="lecture">
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


</body>
<%@ include file="../common/footer.jsp"%>
</html>