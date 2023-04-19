<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTirle" value="게시물 리스트"/>
<%@include file="../common/head.jspf" %>
	
	<table border="1">
		<thead>
			<tr>
				<th>번호</th>
				<th>작성일</th>
				<th>수정일</th>
				<th>작성자</th>
				<th>제목</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="article" items="${articles }">
				<tr>
					<td>${article.id }</td>
					<td>${article.regDate.substring(2, 16)}</td>
					<td>${article.updateDate.substring(2, 16) }</td>
					<td>${article.memberId }</td>
					<td>
						<a href="../article/detail?id=${article.id }">${article.title }</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
<%@include file="../common/foot.jspf" %>