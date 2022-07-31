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
	href="/studyhtml5/favicon.ico">

<!-- jQuery cdn -->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <!-- 사용자 정의 function, ISEmpty -->
    <script src="${CP_RES}/js/eUtil.js"></script>
    <!-- 사용자 정의 function, callAjax -->
    <script src="${CP_RES}/js/eclass.js"></script>

<!--reset 스타일 시트 -->
<!-- <link rel="stylesheet" type="text/css" href="/studyhtml5/asset/css/reset.css"> -->
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap')
	;
</style>
<link rel="stylesheet" type="text/css" href="${CP_RES}/css/header.css">
<link rel="stylesheet" type="text/css" href="${CP_RES}/css/footer.css">
<!--스타일 시트 -->
<style type="text/css">
* {
	margin: 0;
	padding: 0;
	font-family: 'Noto Sans KR', sans-serif;
}

#wrap {
	width: 100%;
	height: 600px;
	position: relative;
	
}

#wrap h1 {margin-bottom: 20px;
    }

.LoginFrm {
	width: 90%;
	height: 300px;
	text-align: center;
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
}

.loginbox {
	width: 60%;
	height: auto;
	margin: 0 auto;
	border-top: 1px solid lightgray;
	padding-top: 20px;
}

.idbox {
	width: 500px;
	margin: 0 auto;
	margin-top: 20px;
}

.idbox input {
	width: 400px;
	display: block;
	box-sizing: border-box;
	height: 30px;
	border: 1px solid #dedede;
}

.idbox label {
	text-align: left;
	width: 100px;
	height: 30px;
	line-height: 30px;
	display: block;
	float: left;
}

.msgErr1, .msgErr2 {
	color: #37385d;
	margin-top: 0;
	text-align: left;
	margin-left: 100px;
	font-size: 13px;
}

.pwbox {
	width: 500px;
	margin: 0 auto;
	margin-top: 20px;
}

.pwbox input {
	width: 400px;
	display: block;
	box-sizing: border-box;
	height: 30px;
	border: 1px solid #dedede;
}

.pwbox label {
	text-align: left;
	width: 100px;
	height: 30px;
	line-height: 30px;
	display: block;
	float: left;
}

.bt {
	width: 100%;
	display: flex;
	justify-content: center;
	margin: 0 auto;
	padding-top: 20px;
	margin-top: 20px;
}

.bt input {
	background: white;
	border: 1px solid #333;
	border-radius: 2px;
	width: 100px;
	height: 30px;
}

#findId {margin-right: 15px;}

#findPass {margin-right: 15px;}

#modal {

  position: absolute;
  top:0;
  left:0;
  
  width: 100%;
  height: 100%;
  
  display: none;
  
  margin: 0 auto;
  background: rgba(0,0,0,0.3);
}

#modal2 {
  position: absolute;
  top:0;
  left:0;
  
  width: 100%;
  height: 100%;
  
  display: none;
  
  margin: 0 auto;
  background: rgba(0,0,0,0.3);
}

.modalContent {
background: white;
  position: absolute;
  top: 50%;
  left: 50%;
  
  height: 250px;
  width: 400px;
  
  text-align: center;
  
  border-radius: 10px;
  box-shadow: 1rem 1rem 1rem 0 rgb(68 68 68 / 20%);
  
  transform: translate(-50%,-50%);
  
  margin: 0 auto;
}

.modalContent h2 {margin-top: 15px; margin-bottom: 15px; border-bottom: 1px solid lightgray;
    display: block; width: 200px; padding-bottom: 10px; margin: 0 auto; padding-top: 10px;}

.modalInbox {background: white; width: 380px; margin: 0 auto;
    margin-top: 20px;}
.modalInbox label {text-align: left;
    width: 120px;
    height: 30px;
    line-height: 30px;
    display: block;
    float: left;
    font-size: 15px;}
    
.modalInbox input { width: 260px;
    display: block;
    box-sizing: border-box;
    height: 30px;
    border: 1px solid #dedede;}

.modalbt {
    width: 180px;
    display: flex;
    justify-content: space-between;
    margin: 0 auto;
    margin-top: 20px;
}
.modalbt input {
    background: white;
    border: 1px solid #333;
    border-radius: 2px;
    width: 80px;
    height: 30px;
}

#modal .modalContent {height: 400px;}
   
</style>

<title>KEMIE</title>
            
    <script type="text/javascript">
      $(document).ready(function(){
        console.log("document.ready");  
        
        $("#exitID").click(function(){
        	$('#modal2').css('display', 'none');
        });
        
        $("#findId").click(function(){
        	$('#modal2').css('display', 'block');
        });
        
        $("#exitPW").click(function(){
            console.log("modalContent clicked!");
            $('#modal').css('display', 'none');
          });
          
          $("#findPass").click(function(){
            console.log("findPass clicked!");
            $('#modal').css('display', 'block');
          });
        
        //비번찾기 팝업
        $("#selectID").click(function(){
            if(eUtil.ISEmpty($("#modal2Name").val())){
                alert("이름을 입력 하세요.")
                $("#modalName").focus();
                return;
            }   
            
            if(eUtil.ISEmpty($("#modal2PNum").val())){
                alert("전화번호를 입력 하세요.")
                $("#modalPNum").focus();
                return;
            }   
          
            let url = "${CP}/login/doFindID.do";
              let method = "POST";
              let async  = true;
              let parameters = {
                      "name": $("#modal2Name").val(),
                      "pNum": $("#modal2PNum").val()
              };
              
              EClass.callAjax(url, parameters, method, async, function(data) {
                  console.log(data);
            	    if("10" == data.msgId){// 이름 확인
                	    console.log("data.msg는 10번!")
                      alert(data.msgContents);
                      $("#modal2UId").focus();
                  }else if("20" == data.msgId){// 전화 번호
                	    console.log("data.msg는 10번!")
                      alert(data.msgContents);
                      $("#modal2Name").focus();
                  }else{
                	  console.log("else문을 탓씁니다.")
                    alert(data.msgContents);
                    $('#modal2').css('display', 'none');
                  }
              });
        });
          
        //비번찾기 팝업
        $("#updatePW").click(function(){
        	console.log("$('#modalPassword').val()"+$('#modalPassword').val());
        	console.log("$('#modalPasswordCheck').val()"+$('#modalPasswordCheck').val());
        	
        	if(eUtil.ISEmpty($("#modalUId").val())){
                alert("아이디를 입력 하세요.")
                $("#modalUId").focus();
                return;
            }
            
            if(eUtil.ISEmpty($("#modalName").val())){
                alert("이름을 입력 하세요.")
                $("#modalName").focus();
                return;
            }   
            
            if(eUtil.ISEmpty($("#modalPNum").val())){
                alert("전화번호를 입력 하세요.")
                $("#modalPNum").focus();
                return;
            }   
        	
            if(eUtil.ISEmpty($("#modalPassword").val())){
                alert("비밀번호를 입력 하세요.")
                $("#modalPassword").focus();
                return;
            }  
            
            if(eUtil.ISEmpty($("#modalPasswordCheck").val())){
                alert("비밀번호 확인을 입력 하세요.")
                $("#modalPasswordCheck").focus();
                return;
            }  
            
		       	if($("#modalPassword").val() != $("#modalPasswordCheck").val()){
		       		alert("비밀번호가 일치하지 않습니다!");
		       		$('#modalPassword').focus();
		       		$('#modalPassword').val("");
		       		$('#modalPasswordCheck').val("");
		       		return;
		       	}
        	
	        	let url = "${CP}/login/doUpdatePW.do";
	            let method = "POST";
	            let async  = true;
	            let parameters = {
	                    "uId": $("#modalUId").val(),
	                    "name": $("#modalName").val(),
	                    "pNum": $("#modalPNum").val(),
	                    "passwd":$("#modalPassword").val()
	            };
	            
	            EClass.callAjax(url, parameters, method, async, function(data) {
	                if("10" == data.msgId){// ID 확인
	                    alert(data.msgContents);
	                    $("#modalUId").focus();
	                }else if("20" == data.msgId){// 이름 확인
	                    alert(data.msgContents);
	                    $("#modalName").focus();
	                }else if("30" == data.msgId){// 전화번호 확인
	                    alert(data.msgContents);
	                    $("#modalPNum").focus();
	                    //특정페이지로 이동: main.jsp
	                }else if("40" == data.msgId){// 이전 비밀번호와 동일한 비밀번호
	                    alert(data.msgContents);
	                    $("#passwd").focus();
	                }else{
	                	alert(data.msgContents);
	                	$('#modal').css('display', 'none');
	                }
	            });
        });
        
        //검색어 Enter
        $("#passwd").on("keypress",function(e){
            console.log("passwd"+e.which);  
            if(13==e.which){
                e.preventDefault();
              //trigger통한 호출: 로그인 호출
                $( "#doLogin" ).trigger( "click" );
            }
        });
        
        $("#doLogin").on("click",function(){
            console.log("doLogin");
            
            if(eUtil.ISEmpty($("#uId").val())){
                alert("아이디를 입력 하세요.")
                $("#uId").focus();
                return;
            }
            
            if(eUtil.ISEmpty($("#passwd").val())){
                alert("비밀번호를 입력 하세요.")
                $("#passwd").focus();
                return;
            }           
            
            if(confirm("로그인 하시겠습니까?")==false)return;
            
            let url = "${CP}/login/doLogin.do";
            let method = "POST";
            let async  = true;
            let parameters = {
                    "uId": $("#uId").val(),
                    "passwd":$("#passwd").val()
            };
            
            EClass.callAjax(url, parameters, method, async, function(data) {
                
                if("10" == data.msgId){//ID확인
                    alert(data.msgContents);
                    $("#uId").focus();
                }else if("20" == data.msgId){//비번확인
                    alert(data.msgContents);
                    $("#passwd").focus();
                }else if("30" == data.msgId){//id/비번 통과
                    alert(data.msgContents);
                    //특정페이지로 이동: main.jsp
                    
                    window.location.href ="${CP}/mainPage.do";
                    
                }else{
                    alert(data.msgContents);
                    $("#uId").focus();
                } 
            });
        });
      });
    </script>
</head>

<body>
    <%@include file="header.jsp" %>
    <script type="text/javascript" src="${CP_RES}/js/header.js"></script>
    <div id="wrap">
        <form action="${CP}/login/doLogin.do" class="LoginFrm" method="post">
            <h1>로그인</h1>
            <div class="loginbox">
                <div class="idbox">
                    <label for="uId">아이디</label> 
                    <input id="uId" type="text" placeholder="아이디를 입력하세요">
                    <p class="msgErr1"></p>
                </div>
                <div class="pwbox">
                    <label for="passwd">비밀번호</label>
                    <input id="passwd" type="password" placeholder="비밀번호를 입력하세요" autocomplete="off" >
                    <p class="msgErr2"></p>
                </div>
            </div>
            <div class="bt">
                <input type="button" value="아이디 찾기" name="findId" id="findId" >
                <input type="button" value="비밀번호 찾기" name="findPass" id="findPass" >
                <input type="button" value="로그인" name="doLogin" id="doLogin" onclick="doLogin" >
            </div>
        </form>
        <div id="modal">
          <div class="modalContent" title="클릭하면 창이 닫힙니다.">
            <h2>비밀번호 찾기</h2>
            <div class="modalInbox">
            <label for="modalUId">아이디</label><input id="modalUId" type="text" placeholder=" 아이디를 입력하세요"><br/>
            <label for="modalName">이름</label><input id="modalName" type="text" placeholder=" 이름을 입력하세요" autocomplete="off" ><br/>
            <label for="modalPNum">전화번호</label><input id="modalPNum" type="text" placeholder=" 전화번호를 입력하세요(예. 010-1234-1234)" autocomplete="off"><br/>
            <label for="modalPassword">변경할 비밀번호</label><input id="modalPassword" type="password" placeholder=" 변경할 비밀번호 입력" autocomplete="off"><br/>
            <label for="modalPasswordCheck">비밀번호 확인</label><input id="modalPasswordCheck" type="password" placeholder=" 비밀번호 확인" autocomplete="off"><br/>
            </div>
            <div class="modalbt">
            <input type="button" value="비밀번호 변경" id="updatePW" /><input type="button" value="닫기" id="exitPW" /> 
            </div>
          </div>
        </div>
        <div id="modal2">
          <div class="modalContent" title="클릭하면 창이 닫힙니다.">
            <h2>아이디 찾기</h2>
            <div class="modalInbox">
            <label for="modal2Name">이름</label><input id="modal2Name" type="text" placeholder=" 이름을 입력하세요" autocomplete="off" ><br/>
            <label for="modal2PNum">전화번호</label><input id="modal2PNum" type="text" placeholder=" 전화번호를 입력하세요(예. 010-1234-1234)" autocomplete="off"><br/>
            </div>
            <div class="modalbt">
            <input type="button" value="아이디 찾기" id="selectID" /><input type="button" value="닫기" id="exitID" /> 
            </div>
          </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</body>
</html>