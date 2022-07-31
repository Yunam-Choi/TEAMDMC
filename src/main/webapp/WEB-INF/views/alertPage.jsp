<%--
/**
	Class Name:
	Description:
	Modification information
	
	수정일     수정자      수정내용
    -----   -----  -------------------------------------------
    2022. 7. 7.        최초작성 
    
    author TEAMDMC 개발팀
    since 2022.06.20
    Copyright (C) by NaSeulGi All right reserved.
*/
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="CP" value="${pageContext.request.contextPath}" />
<c:set var="resources" value="/resources/" />    
<c:set var="CP_RES" value="${CP}${resources}"/>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
   <link rel="shortcut icon" type="image/x-icon" href="${CP}/favicon.ico">
   <!-- jQuery cdn -->
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.highcharts.com/stock/highstock.js"></script>
	<script src="https://code.highcharts.com/stock/modules/data.js"></script>
	<script src="https://code.highcharts.com/stock/modules/exporting.js"></script>
    <script type="text/javascript">
      $(document).ready(function() {
    	    if(alert("로그인 후 사용 가능한 페이지입니다. 확인을 누르면 로그인 페이지로 이동합니다.")==true){
    	    	window.location.href="${CP}/login/loginView.do";
    	    }else{
    	    	window.location.href="${CP}/login/loginView.do";
    	    }
      });
    </script>
    <title>KEMIE</title>
</head>

<body>
	<%@include file="header.jsp" %>
	<script type="text/javascript" src="${CP_RES}/js/header.js"></script>
	<%@include file="footer.jsp" %>
</body>

</html>