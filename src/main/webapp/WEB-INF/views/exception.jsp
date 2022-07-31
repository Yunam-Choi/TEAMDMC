<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="CP" value="${pageContext.request.contextPath }"></c:set>
<c:set var="resources" value="/resources"></c:set>
<c:set var="CP_RES" value="${CP}${resources}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon"
    href="/studyhtml5/favicon (3).ico">

<style>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');
</style>
<!--reset 스타일 시트 -->
<!--<link rel="stylesheet" type="text/css" href="/studyhtml5/asset/css/reset.css">  -->
<!--스타일 시트 -->
<link rel="stylesheet" type="text/css" href="${CP_RES}/css/header.css">
<link rel="stylesheet" type="text/css" href="${CP_RES}/css/footer.css"><style type="text/css">
* {
    margin: 0;
    padding: 0;
    font-family: 'Noto Sans KR', sans-serif;
}
#wrap {
    width: 100%;
    height: calc(100vh - 80px);
    position: relative;
    background-image: url("${CP_RES}/img/kemieLOGO.png");
}
.exception {width: 100%; text-ailgn: center;}
</style>
<title>KEMIE</title>

<!--jquery-->
<script type="text/javascript"
    src="/studyhtml5/asset/js/jquery-1.12.4.js"></script>


</head>
<body>
    <%@include file="header.jsp" %>
    <script type="text/javascript" src="${CP_RES}/js/header.js"></script>
    <!-- 내용 -->
    <div id="wrap">
       <div class="exception">
            <%--<div class="imgBox"><img src="${CP_RES}/img/kemieLOGO.png"></div>  --%>
            <div class="msgBox">
                <h3>요청 과정에서 에러가 발생했습니다.</h3>
                    <p>상태 코드 : <c:out value="${requestScope['javax.servlet.error.status_code'] }"/> </p>
                    <p>서블릿 이름 : <c:out value="${requestScope['javax.servlet.error.servlet_name'] }"/> </p>
                    <p>예외 이름 : <c:out value="${requestScope['javax.servlet.error.exception'] }"/> </p>
                    <p>요청URI : <c:out value="${requestScope['javax.servlet.error.request_uri'] }"/> </p>
                    <p>메시지 : <c:out value="${requestScope['javax.servlet.error.message'] }"/> </p>
            </div>
       </div>
    </div>
    <!-- 내용 ----------------------------------------------------------->
    <%@include file="footer.jsp" %>
    <!--자바스크립트 코드 -->
</body>
</html>