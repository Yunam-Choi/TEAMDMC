<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="CP" value="${pageContext.request.contextPath }"></c:set>
<c:set var="resources" value="/resources"></c:set>
<c:set var="CP_RES" value="${CP}${resources}"></c:set>
<!--html comment-->
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- jQuery cdn -->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script src="${CP_RES}/js/eUtil.js"></script>
<script src="${CP_RES}/js/eclass.js"></script>
<!-- jquery bootstrap paging -->
<script type="text/javascript" src="${CP_RES}/js/jquery.bootpag.js"></script>


<!--자바스크립트 코드-->
<script type="text/javascript">
    $(document).ready(function() {
 
        renderingPage('${pageTotal}', 1);

        // 데이터 클릭
        $(".board>tbody").on("click","tr", function(e) {
          console.log("tr click~");
          let tdArray = $(this).children();
          let boardSeq = tdArray.eq(5).text();
          console.log("boardSeq : " +boardSeq);

          window.location.href = "${CP}/board/doSelectOne.do?bSeq=" +boardSeq;

       });
        
        // 등록화면으로 이동
        $("#moveToReg").on("click", function(e) {
           console.log("moveToReg");
           window.location.href = "${CP}/board/moveToReg.do"; // 특정 url로 이동! 아이디 div안의 값(val)을 가져와라~
       });

        // paging
        function renderingPage(pageTotal, page) {
            console.log("pageTotal : " + pageTotal);
            console.log("page : " + page);
            
            pageTotal = parseInt(pageTotal);
            console.log("pageTotal : " + pageTotal);
            
            // 이전에 연결된 EventHandler 제거
            $('#page-selection').unbind('page');
            
            $('#page-selection').bootpag({
                total: pageTotal,
                page: page,
                maxVisible: 10,
                leaps: true,
                firstLastUse: true,
                first: '←',
                last: '→',
                wrapClass: 'pagination',
                activeClass: 'active',
                disabledClass: 'disabled',
                nextClass: 'next',
                prevClass: 'prev',
                lastClass: 'last',
                firstClass: 'first'
            }).on("page", function(event, num){
                console.log("num : " + num);
                doRetrieve(num);
            }); 
        }

        $("#searchWord").on("keypress", function(e) {
            console.log("searchword " + e.which);
            if (13 == e.which) {
                e.preventDefault();
                doRetrieve(1);
            }
        });

        function doRetrieve(page) {
            console.log("function doRetrieve");
            console.log("page : " + page);

            let url = "${CP}/board/doRetrieve.do";
            let method = "GET";
            let async = true;
            let parameters = {
                searchDiv: $("#searchDiv").val(),
                searchWord: $("#searchWord").val(),
                pageSize: $("#pageSize").val(),
                pageNum: page
            };

            EClass.callAjax(url, parameters, method, async, function(data) {
                console.log("EClass.callAjax data :" + data);
                 
                 let parsedData = data;

                 
                 $(".board>tbody").empty();
                 console.log("parsedData : " + parsedData.length);
                 
                 let htmlData = ""; // 동적으로 tbody 아래에 데이터를 생성하기위한 변수

                 let totalCnt = 0; // 총글수
                 let pageTotal = 1; // 페이지수

                 if (null != parsedData && parsedData.length > 0) {

                	 totalCnt = parsedData[0].totalCnt;
                     console.log('totalCnt : ' + totalCnt);
                     
                     pageTotal = totalCnt/$("#pageSize").val();
                     console.log('pageTotal' + pageTotal);
                     pageTotal = Math.ceil(pageTotal);
                     console.log('pageTotal' + pageTotal);
                     
                 // each: 제이쿼리에서 쓰는 뺑뺑이! (like for문)
                 // parsedData
                 $.each(parsedData, function(i, boardVO) {
                     htmlData += "<tr>";
                     htmlData += "<td>" + <c:out value = 'boardVO.num'/>+"</td>";
                     htmlData += "<td>" + <c:out value = 'boardVO.bTitle'/>+"</td>";
                     htmlData += "<td>" + <c:out value = 'boardVO.uNick'/>+"</td>";
                     htmlData += "<td>" + <c:out value = 'boardVO.regDt'/>+"</td>";
                     htmlData += "<td>" + <c:out value = 'boardVO.bReadCnt'/>+"</td>";
                     htmlData += "<td style='display: none;'>"+<c:out value = 'boardVO.bSeq'/>+"</td>";
                     htmlData += "</tr>";
                 });

                 } else { // 데이터가 없는 경우
                     htmlData += "<tr><td colspan='99' class='text-center'>No data found!</td></tr>";
                 }
                 
                 // board_table > tbody에 htmlData를 넣어라!
                 $(".board>tbody").append(htmlData);

                 // paging
                 // 기존 페이징 지우기
                 $("#page-selection").empty();

                 // paging 호출
                 renderingPage(pageTotal, page);
                 
              });

         }

        $("#bt2").on("click", function(e) {
        console.log("얍얍");
        doRetrieve(1);
        });

        });
    
</script>

<head>
<style>
@import
    url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap')
    ;
</style>

<link rel="stylesheet" type="text/css" href="${CP_RES}/css/header.css">
<link rel="stylesheet" type="text/css" href="${CP_RES}/css/footer.css">
<!--스타일시트 -->
<style type="text/css">
* {
    margin: 0;
    padding: 0;
    font-family: 'Noto Sans KR', sans-serif;
}

#wrap {
    width: 100%;
    height: 830px;
    margin-top: 20px;
}

.top {
    width: 90%;
    height: 100px;
    margin: 0 auto;
    position: relative;
}

.search {
    width: 150px;
    height: 43px;
    text-align: center;
    box-sizing: border-box;
    border: 1px solid lightgray;
    margin-right: 10px;
}

.textbox {
    width: 250px;
    height: 43px;
    box-sizing: border-box;
    border: 1px solid lightgray;
    margin-right: 10px;
}

.scbox {
    position: absolute;
    right: 0;
    top: 10px;
}

.bt1 {
    height: 45px;
    width: 100px;
    line-height: 45px;
    color: #fff;
    font-size: 15px;
    background-color: #37385d;
    border-radius: 2px;
    box-sizing: border-box;
    border: none;
    position: absolute;
    left: 0;
    top: 10px;
}

.bt2 {
    height: 45px;
    width: 100px;
    line-height: 45px;
    color: #fff;
    font-size: 15px;
    background-color: #37385d;
    border-radius: 2px;
    box-sizing: border-box;
    border: none;
}

.board {
    width: 90%;
    height: auto;
    margin: 0 auto;
}

table, td, th {
    border-collapse: collapse;
}

table {
    border-spacing: 0;
    border-top: 2px solid #333333;
    border-bottom: 2px solid #333333;
}

td, th {
    text-align: center;
}

th:nth-child(1) {
    width: 8%;
}
th:nth-child(2) {
    width: 50%;
}
th:nth-child(3) {
    width: 15%;
}
th:nth-child(4) {
    width: 20%;
}
th:nth-child(5) {
    width: 7%;
}

td:nth-child(2) {
    text-align: left;
}

tr {
    border-top: 1px solid lightgray;
    height: 30px;
}

#header a, #footer a {
    text-decoration: none;
    color: white;
    font-weight: 500;
    -webkit-font-smoothing: antialiased;
    font-size: 17px;
}

#header a:hover {
   color: #0062df;
    font-size: 18px;
    font-weight: 800;
    text-decoration: none;
}

#footer a:hover {
   color: #0062df;
    font-size: 18px;
    font-weight: 800;
    text-decoration: none;
}

#footer {margin: 0; padding: 0;}

#footer p{margin: 0; padding: 0;}
</style>

<title>KEMIE</title>


</head>

<body>
    <%@include file="header.jsp"%>
    <script type="text/javascript" src="${CP_RES}/js/header.js"></script>
    <!-- 전체 박스 시작 -->
    <div id="wrap">
        <!-- 글쓰기, 검색영역 -->
        <div class="top">
            <button class="bt1" id="moveToReg">글쓰기</button>
            <!-- 검색영역 div -->
            <div class="scbox">
                <select class="search" id="searchDiv" name="searchDiv">
                    <option value="10">제목</option>
                    <option value="20">내용</option>
                    <option value="30">제목 + 내용</option>
                    <option value="40">닉네임</option>
                </select> 
                <input type="text" class="textbox" id="searchWord" name="searchWord" placeholder="  검색어를 입력해주세요.">
                <input type="submit" class="bt2" id="bt2" name="bt2" value="검색">
                <input type="hidden" id="pageSize" name="pageSize" value="20">
            </div>
            <!-- 검색영역 div 끝 -->
        </div>
        <!-- 글쓰기, 검색영역 끝 -->
        <!-- 게시판 목록 영역 -->
        <table class="board">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>작성일</th>
                    <th>조회수</th>
                    <th style="display: none;">bSeq</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                <c:when test="${list.size()>0}">
                <c:forEach var="vo" items="${list}">
                <tr>
                    <td>${vo.num}</td>
                    <td>${vo.bTitle}</td>
                    <td>${vo.uNick}</td>
                    <td>${vo.regDt}</td>
                    <td>${vo.bReadCnt}</td>
                    <td style="display: none;">${vo.bSeq}</td>
                </tr>
                </c:forEach>
                </c:when>                   
                <c:otherwise>
                    <tr><td colspan="99" class="text-center">No data found!</td></tr>
                </c:otherwise>
                </c:choose>
            </tbody>
        </table>
        <!-- 게시판 목록 끝 -->
        <!-- 페이징 영역 -->
        <!-- pagenation -->
        <div class="text-center col-sm-12 col-md-12 col-lg-12">
            <div id="page-selection" class="text-center page"></div>
        </div>
        <!--//pagenation ------------------------------------------------------>
        <!-- 페이징 끝 -->
    </div>
    <!-- 전체영역 끝 -->
    <%@include file="footer.jsp"%>
</body>

</html>