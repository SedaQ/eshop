<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Language School Lecture</title>
<%@ include file="../common/head.jsp"%>
</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<table class="table">
		<thead>
			<tr>
				<th>id</th>
				<th>dayTime</th>
				<th>classroomId</th>
				<th>topic</th>
			</tr>
		</thead>

		<tbody>
			<tr>
				<td>${lecture.id}</td>
				<td><c:out value="${lecture.dayTime}" /></td>
				<td><c:out value="${lecture.classroomId}" /></td>
				<td><c:out value="${lecture.topic}" /></td>
				<td><a
					href="${pageContext.request.contextPath}/lecture/edit/${lecture.id}"
					class="btn btn-primary">Edit</a> <a
					href="${pageContext.request.contextPath}/lecture/delete/${lecture.id}"
					class="btn btn-primary">Delete</a></td>
			</tr>
		</tbody>
	</table>

	<%@ include file="../common/footer.jsp"%>
</body>
</html>