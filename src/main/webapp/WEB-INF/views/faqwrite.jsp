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
<!-- 사용자 정의 function, callAjax -->
<script src="${CP_RES}/js/eUtil.js"></script>
<script src="${CP_RES}/js/eclass.js"></script>

<!--자바스크립트 코드 -->
<script type="text/javascript">
    $(document).ready(function(){
        console.log("document.ready");
        
        let fileTarget = $("#fileinput");
        
        fileTarget.on("change", function() {
            if(window.FileReader) {
                var filename = $(this)[0].files[0].name;
            }
            $(this).siblings("#fileadd").val(filename);
            console.log($(this).siblings("#fileadd").val());
        });
        
        fileTarget.on("change", function(){
            if(this.files && this.files[0]) {
                let reader = new FileReader;
                reader.onload = function(data) {
                    $(".select_img img").attr("src", data.target.result).width(500);
                }
                reader.readAsDataURL(this.files[0]);
            }
        });
    
        
        function moveToList() {
            window.location.href="${CP}/faq/faqView.do";
        }
        
        $("#moveToList").on("click", function(){
            moveToList();
        });
        
        $("#doInsert").on("click", function(e){
            console.log("insert???");
            
        // 필수값 체크
        if(eUtil.ISEmpty($("#title").val())){
            alert("제목을 입력하세요."); 
            $("#title").focus();
            return;
        }
        
        if(eUtil.ISEmpty($("#contents").val())){
            alert("내용을 입력하세요."); 
            $("#contents").focus();
            return;
        }
        
        if(confirm("등록하시겠습니까?")==false)return;
        
        let url = "${CP}/faq/doInsert.do";
        let method = "POST";
        let parameters = {
                "uId":$("#admin1").val(),
                "fTitle":$("#title").val(),
                "fContents":$("#contents").val()
        };
        
        let async = true;
        
        EClass.callAjax(url,parameters,method,async,function(data) {
            console.log("data: " + data);
            console.log("data.msgId : " + data.msgId);
            console.log("data.msgContents : " + data.msgContents);
            if("1"==data.msgId){
                alert(data.msgContents);
                moveToList();
            }else{
                alert(data.msgContents);
            }
        });
        
        
        });
        
     });   
</script>

<head>
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

#wrap {
    width: 100%;
    height: calc(100vh - 80px);
    position: relative;
}

.box {
    width: 90%;
    height: 850;
    font-size: 16px;
    margin: 0 auto;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
}

.bu {
    width: 90%;
    height: 50px;
    text-align: right;
    margin: 0 auto;
    display: flex;
    align-items: center;
    justify-content: right;
}

#moveToList {
    width: 50px;
    height: 30px;
    border: 1px solid #333333;
    background: white;
    border-radius: 2px;
    margin-left: 10px;
}

#doInsert {
    width: 50px;
    height: 30px;
    border: 1px solid #333333;
    background: white;
    border-radius: 2px;
    margin-left: 10px;
}

.titlebox {
    width: 100%;
    height: 70px;
    margin: 0 auto;
    display: flex;
    justify-content: center;
    align-items: center;
}

.titlebox input {
    height: 40px;
    width: 90%;
    border: none;
    border-bottom: 1px solid #333;
    font-size: 15px;
}

.writebox {
    width: 90%;
    background: skyblue;
    margin: 0 auto;
    display: flex;
    justify-content: center;
    align-items: center;
}

.writebox textarea {
    height: 400px;
    width: 100%;
    border: 1px solid #333;
}

.filebox {
    width: 90%;
    height: 40px;
    margin: 0 auto;
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-top: 20px;
    
}

#fileinput {
    width: 1px;
    height: 1px;
    padding: 0;
    margin: -1px;
    overflow: hidden;
    clip: rect(0,0,0,0);
    border: 0;
}

#fileadd {
    width: 89%;
    height: 40px;
    border: 1px solid #333;
    border-radius: 2px;
    box-sizing: border-box;
    font-size: 15px;
    line-height: 40px;
}

#addbt {
    width: 10%;
    height: 40px;
    border: 1px solid #333;
    border-radius: 2px;
    box-sizing: border-box;
    font-size: 15px;
    line-height: 40px;
    text-align: center;
}

.simg {
    width: 90%;
    height: 250px;
    margin: 0 auto;
    margin-top: 20px;
    }
    
.select_img {
    width: 250px;
    height: 250px;
    border : 1px solid #333;
    box-sizing: border-box;
    overflow: hidden;
    position: relative;
    
}

.select_img img {
    width: 100%;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
}


#wrap a {text-decoration: none; color: black;}
</style>
<title>KEMIE</title>

</head>
<body>
    <%@include file="header.jsp" %>
    <script type="text/javascript" src="${CP_RES}/js/header.js"></script>
    <!-- 내용 -->
    <div id="wrap">
        <div class="box">
            <!-- button -->
            <div class="bu">
                <input type="button" id="moveToList" value="목록"/>
                <input type="button" id="doInsert" value="등록"/>
            </div>
            <!-- button -------------------------------------------------->
                 <input type="hidden" id="admin1" value="${sessionScope.user.uId}">
            <div class="titlebox">
                <!-- title -->
                <input type="text" id="title" placeholder="제목을 입력하세요">
            </div>
            <!-- title -------------------------------------------------->
            <!-- contents -->
            <div class="writebox">
                <textarea name="contents" id="contents"></textarea>
            </div>
            <!-- contents ----------------------------------------------->
           
        </div>
    </div>
    <!-- 내용 ----------------------------------------------------------->
    <%@include file="footer.jsp" %>

</body>
</html>