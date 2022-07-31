<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="CP" value="${pageContext.request.contextPath }"></c:set>
<c:set var="resources" value="/resources"></c:set>
<c:set var="CP_RES" value="${CP}${resources}"></c:set>
<!DOCTYPE html>
<html>

<meta name="viewport" content="width=device-width,initial-scale=1.0">
<meta charset="UTF-8">
<!-- jQuery cdn -->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="${CP_RES}/js/eUtil.js"></script>
<script src="${CP_RES}/js/eclass.js"></script>

<!--자바스크립트 코드-->
<script type="text/javascript">
 $(document).ready(function(){
    console.log("document ready");
    
    
    function moveToList() {
        window.location.href="${CP}/board/boardView.do";
    }
    
    function moveToDetails() {
        let boardSeq = $("#bSeq").val();
        window.location.href="${CP}/board/doSelectOne.do?bSeq="+boardSeq;
    }
    
    $("#btnList").on("click", function(){
        moveToList();
    });
    
    $("#btnUpdate").on("click", function(){
        let boardSeq = $("#bSeq").val();
        window.location.href="${CP}/board/doMod.do?bSeq="+boardSeq;
    });
  
    $("#btnDelete").on("click",function(e){
        console.log("doDelete");
        let boardId = $("#uId").val();
        let boardSeq = $("#bSeq").val();
        console.log("boardId:"+boardId);
        console.log("boardSeq:"+boardSeq);
        
        if(confirm("게시물을 삭제 하시겠습니까?")==false)return;
        
        let url = "${CP}/board/doDelete.do";
        let method = "POST";
        let async  = true;
        let parameters = {
                "uId":boardId,
                "bSeq":boardSeq
        };
        
        EClass.callAjax(url, parameters, method, async, function(data) {
            console.log("data.msgId:"+data.msgId);
            console.log("data.msgContents:"+data.msgContents);     
            if("1" == data.msgId){
                alert(data.msgContents);
                moveToList();
            }else{
                
                alert(data.msgContents);
            }                
                            
        });       
          
    });
    
    $("#btnReply").on("click", function() {
        console.log("댓글 등록");
        let boardSeq = $("#bSeq").val();
         
         if(eUtil.ISEmpty($("#bcContents").val())){
             alert("내용을 입력하세요."); 
             $("#bcContents").focus();
             return;
         }
         
         if(confirm("등록하시겠습니까?")==false)return;
         
         let url = "${CP}/board/commentInsert.do";
         let method = "POST";
         let parameters = {
                 "bSeq":boardSeq,
                 "uNick":"${sessionScope.user.nick}",
                 "uId":"${sessionScope.user.uId}",
                 "bcContents":$("#bcContents").val()
         };
         
         let async = true;
         
         EClass.callAjax(url,parameters,method,async,function(data) {
             console.log("data.msgId : " + data.msgId);
             console.log("data.msgContents : " + data.msgContents);
             if("1"==data.msgId){
                 alert(data.msgContents);
                 moveToDetails();
             }else{
                 alert(data.msgContents);
             }
         });
        
    });
    
    $("[id*='replyRemove']").on("click", function() {
    
        console.log($(this).parent().siblings("[id*='bcUid']").text());
        console.log($(this).parent().siblings("[id*='bcSeq']").text());
        
        if(confirm("댓글을 삭제 하시겠습니까?")==false)return;
        
        let url = "${CP}/board/commentDelete.do";
        let method = "POST";
        let async  = true;
        let parameters = {
                "uId":$(this).parent().siblings("[id*='bcUid']").text(),
                "bcSeq":$(this).parent().siblings("[id*='bcSeq']").text(),
                "bSeq":$(this).parent().siblings("[id*='bcBSeq']").text()
        };
        
        EClass.callAjax(url, parameters, method, async, function(data) {
            console.log("data.msgId:"+data.msgId);
            console.log("data.msgContents:"+data.msgContents);     
            if("1" == data.msgId){
                alert(data.msgContents);
                moveToDetails();
            }else{    
                alert(data.msgContents);
            }              
        }); 
        
    });
    
 });
</script>

<head>
<title>KEMIE</title>
<style>
@import
    url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap')
    ;
</style>

<!--스타일 시트 -->
<link rel="stylesheet" type="text/css" href="${CP_RES}/css/header.css">
<link rel="stylesheet" type="text/css" href="${CP_RES}/css/footer.css">
<style type="text/css">
* {
    margin: 0;
    padding: 0;
    font-family: 'Noto Sans KR', sans-serif;
}

#all {
    width: 90%;
    margin: 0 auto;
    margin-top: 30px;
    margin-bottom: 30px;
}

.buttonDiv {
    width: 90%;
    height: 50px;
    margin: 0 auto;
    display: flex;
    align-items: center;
    justify-content: right;
}

.buttonDiv .btn {
    width: 50px;
    height: 30px;
    border: 1px solid #333333;
    background: white;
    border-radius: 2px;
    margin-left: 10px;
}

.main {
    width: 90%;
    border: 1px solid #333;
    box-sizing: content-box;
    height: 500px;
    margin: 0 auto;
}

.boardTitle {
    width: 90%;
    height: 70px;
    line-height: 70px;
    border-bottom: 1px solid #333;
    box-sizing: content-box;
    margin: 0 auto;
}

.boardMulti {
    width: 90%;
    height: 30px;
    line-height: 30px;
    margin: 0 auto;
    font-size: 10px;
    display: flex;
}

.boardMulti p {
    font-weight: bold;
    margin-right: 5px;
}

.boardMulti span {
    margin-right: 15px;
}

.boardContent {
    width: 90%;
    box-sizing: content-box;
    margin: 0 auto;
    height: 320px;
    padding-top: 20px;
}

.commentsList {
    width: 90%;
    margin: 0 auto;
    border: 1px solid #333;
    margin-top: 20px;
    height: auto;
}

.commentOne {
    height: 40px;
    margin: 0 auto;
    border-bottom: 3px solid #dedede;
    
}

.commentsList .nic {
    width: 10%;
    font-size: 15px;
    text-align: center;
}

.commentsList .reply {
    width: 60%;
    font-size: 15px;
}

.commentsList .commentsDate {
    width: 20%;
    font-size: 15px;
    text-align: center;
}

#replyRemove {
    display: inline;
    margin: 0 auto;
    width: 40px;
    height: 20px;
    border: 1px solid #333;
    border-radius: 2px;
    background: white;
    font-size: 12px;
    line-height: 20px;
}

.commentsMain {
    width: 90%;
    border: 1px solid #333;
    box-sizing: content-box;
    margin: 0 auto;
    height: 200px;
    margin-top: 20px;
}

.comments {
    width: 90%;
    margin: 0 auto;
    height: 50px;
    line-height: 50px;
}

.commentsbox {
    width: 90%;
    height: 130px;
    margin: 0 auto;
    display: flex;
    justify-content: space-between;
}

#bcContents {
    width: 87%;
}

#btnReply {
    width: 10%;
}

#all a {text-decoration: none;}
</style>

<!--자바스크립트 코드 -->
<script type="text/javascript">
</script>
</head>

<body>
    <%@include file="header.jsp" %>
    <script type="text/javascript" src="${CP_RES}/js/header.js"></script>
    <!-- main div 열림 -->
    <div id="all">
        <form action="#" method="post">
            <!-- 버튼으로 목록 수정 삭제 만들기 -->
            <div class="buttonDiv">
                <c:choose>
                <c:when test="${vo.uId != sessionScope.user.uId}">
                <button type="button" class="btn btn-sm btn-primary" id="btnList">목록</button>
                </c:when>
                <c:otherwise>
                <button type="button" class="btn btn-sm btn-primary" id="btnDelete">삭제</button>
                <button type="button" class="btn btn-sm btn-primary" id="btnUpdate">수정</button>
                <button type="button" class="btn btn-sm btn-primary" id="btnList">목록</button>
                </c:otherwise>
                </c:choose>
            </div>
            <!-- 버튼으로 목록 수정 삭제 종료 -->
           
            <!-- 제목,작성시간,조회수,작성자,내용 -->
            <div class="main">
                <!-- 제목 -->
                <div class="boardTitle">
                    <table class="boardView">
                        <tr>
                            <td><c:out value='${vo.bTitle}'/></td>
                        </tr>
                    </table>
                </div>
                <!-- 제목 끝 -->
           
                <!-- 멀티 -->
                <div class="boardMulti">
                    <p>등록일</p>
                    <span><c:out value='${vo.regDt}'/></span>
                    <p>작성자</p>
                    <span><c:out value='${vo.uNick}'/></span>
                    <p>조회수</p>
                    <span><c:out value='${vo.bReadCnt}'/></span>
                </div>
                <!-- 멀티 끝 -->
                <input type="hidden" id="uId" value='${sessionScope.user.uId}'>
                <input type="hidden" id="bSeq" value='${vo.bSeq}'>
                <!-- 내용 -->
                <div class="boardContent">
                    <p><c:out value='${vo.bContents}'/></p>
                </div>
                <!-- 내용 끝 -->
            </div>
            <!-- main div끝 -->
        </form>
        <!-- 폼 끝 -->

        <form action="#" method="post">
        <!-- 댓글 목록 -->
        <table class="commentsList">
            <c:choose>
            <c:when test="${comList.size()>0 }">
            <c:forEach items="${comList}" var="comList" varStatus="status">
            <tr class="commentOne">
                <c:choose>
                <c:when test="${comList.uId !=sessionScope.user.uId}">
                    <td class="nic">${comList.uNick }</td>
                    <td class="reply">${comList.bcContents }</td>
                    <td class="commentsDate">${comList.regDt }</td>
                    <td id="bcSeq${status.index}" style="display: none;">${comList.bcSeq }</td>
                    <td id="bcUid${status.index}" style="display: none;">${comList.uId }</td>
                    <td id="bcBSeq${status.index}" style="display: none;">${comList.bSeq }</td>
                    <td class="removebt" align="center"></td>
                </c:when>
                <c:otherwise>
                    <td class="nic">${comList.uNick }</td>
                    <td class="reply">${comList.bcContents }</td>
                    <td class="commentsDate">${comList.regDt }</td>
                    <td id="bcSeq${status.index}" style="display: none;">${comList.bcSeq }</td>
                    <td id="bcUid${status.index}" style="display: none;">${comList.uId }</td>
                    <td id="bcBSeq${status.index}" style="display: none;">${comList.bSeq }</td>
                    <td class="removebt" align="center"><button type="button" id="replyRemove${status.index}" value="삭제" >삭제</button></td>
                </c:otherwise>
                </c:choose>
            </tr>
            </c:forEach>
            </c:when>
            <c:otherwise>
                <tr class="commentOne">
                 <td class="reply">아직 댓글이 없습니다.</td>
                </tr>
            </c:otherwise>
            </c:choose>

        </table>
        </form>
        <!-- 댓글 목록 끝 -->
        <!-- 댓글 작성 -->
        <form class="commentsMain" name="commentsMain" method="post">
           <input type="hidden" id="bSeq" value='${vo.bSeq}'>
           <input type="hidden" id="cuNick" name="cuNick" value="admin"> 
           <input type="hidden" id="cuId" name="cuId" value="admin"> 
            
            <div class="comments">
                <strong>댓글 작성</strong>
            </div>
            <div class="commentsbox">
                <textarea id="bcContents" name="bcContents" placeholder="댓글내용을 입력하세요"></textarea>
                <button type="button" id="btnReply">등록</button>
            </div>
        </form>
        <!-- 댓글 작성 끝 -->
    </div>
    <!-- main div 닫음 -->
    <%@include file="footer.jsp" %>
</body>
</html>