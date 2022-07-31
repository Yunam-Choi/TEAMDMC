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

<!-- jQuery cdn -->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <!-- 사용자 정의 function, ISEmpty -->
    <script src="${CP_RES}/js/eUtil.js"></script>
    <!-- 사용자 정의 function, callAjax -->
    <script src="${CP_RES}/js/eclass.js"></script>

<!--reset 스타일 시트 -->
<!--<link rel="stylesheet" type="text/css" href="/studyhtml5/asset/css/reset.css">  -->
<link rel="stylesheet" type="text/css" href="${CP_RES}/css/header.css">
<link rel="stylesheet" type="text/css" href="${CP_RES}/css/footer.css">
<!--스타일 시트 -->
<style>
@import
    url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap')
    ;
</style>
<style type="text/css">
* {
    margin: 0;
    padding: 0;
    font-family: 'Noto Sans KR', sans-serif;
}
#wrap {
    width: 100%;
    height: 800px;
    position: relative;
}
.txtbox {
    width: 600px;
    height: 630px;
    font-size: 16px;
    margin: 0 auto;
    text-align: center;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
}
.txtbox h1 {
    margin-bottom: 20px;
}
.box {
    width: 100%;
    height: 80px;
    display: flex;
    flex-wrap: wrap;
}
.box input {
    width: 400px;
    display: block;
    box-sizing: border-box;
    height: 30px;
    border: 1px solid #dedede;
}
.box label {
    width: 100px;
    height: 30px;
    line-height: 30px;
    display: block;
    text-align: left;
    font-size: 15px;
}
#idCheckYN {display: none;}
.gaib {
    width: 100%;
    height: 50px;
    text-align: center;
    margin-top: 20px;
}
.gaib input {
    width: 90px;
    height: 30px;
    margin: 0 auto;
    border: 1px solid #333;
    background: white;
    border-radius: 2px;
}
 #idCheck{
    width: 80px;
    height: 30px;
    border-radius: 10px;
    text-align: center;
    font-size: 13px;
    background: #38385d;
    color: white;
    margin-left: 15px;
 }
 #nickCheck{
    width: 80px;
    height: 30px;
    border-radius: 10px;
    text-align: center;
    font-size: 13px;
    background: #38385d;
    color: white;
    margin-left: 15px;
 }
 
 .necess.errMsg {
  display: none;
 }
 
 .necess{
    font-size: 13px;
    color: tomato;
    text-ailgn: right;
    display: block;
    margin-bottom: 20px;
 }
 
 #doInsert {}
</style>
<title>KEMIE</title>

<script type="text/javascript">
  function idCheckDone(input){
    // 아이디 중복확인 여부 체크 -------------------------------------------------------
    if($("#idCheckYN").val() == "0"){
      $('.necess.errMsg.' + input.attr("id")).text("아이디 중복확인을 해주세요.");
      $('.necess.errMsg.' + input.attr("id")).css("display", "block");
      
      return false;
    }
    
    $('.necess.errMsg.' + input.attr("id")).css("display", "none");
    return true;
  }
  function nickCheckDone(input){
    // 아이디 중복확인 여부 체크 -------------------------------------------------------
    if($("#nickCheckYN").val() == "0"){
      $('.necess.errMsg.' + input.attr("id")).text("닉네임 중복확인을 해주세요.");
      $('.necess.errMsg.' + input.attr("id")).css("display", "block");
      
      return false;
    }
    
    $('.necess.errMsg.' + input.attr("id")).css("display", "none");
    return true;
  }
  
  function uCheckDone(){
    if(!idCheckDone($('input[id="uId"]'))) return '1';
    if(!nickCheckDone($('input[id="nick"]'))) return '2';
    
    return '3';
  }
  
  function doubleCheck(input){
    console.log(input);
    // 더블 체크: 공란 체크, 포맷 체크
    //     만약 passCheck일 경우: 공란 체크, 비밀번호가 같은지 체크
    if(input.attr("id") == "passCheck"){
      if(!blankCheck(input))  return false;
      console.log("1");
      if(!passwdCheck(input)) return false;
      console.log("2");
    }
    
    if(!blankCheck(input))  return false;
    console.log("3");
    if(!formatCheck(input)) return false;
    console.log("4");
    
    $('.necess.errMsg.' + input.attr("id")).css("display", "none");
    return true;
  }
  function lastInsertCheck(){
    // 아이디 더블 체크 -------------------------------------------------------------
    let selector = $('input[id="uId"]');
    if(!doubleCheck(selector)) {
      selector.focus();
      return false;
    }
    
    // 비밀번호 더블 체크 ------------------------------------------------------------
    selector = $('input[id="passwd"]');
    if(!doubleCheck(selector)) {
      selector.focus();
      return false;
    }
    
    // 비밀번호 확인 더블 체크 ---------------------------------------------------------
    selector = $('input[id="passCheck"]');
    if(!doubleCheck(selector)) {
      selector.focus();
      return false;
    }
    
    // 이름 더블 체크 ---------------------------------------------------------------
    selector = $('input[id="name"]');
    if(!doubleCheck(selector)) {
      selector.focus();
      return false;
    }
    
    // 전화번호 더블 체크 ------------------------------------------------------------
    selector = $('input[id="pNum"]');
    if(!doubleCheck(selector)) {
      selector.focus();
      return false;
    }
    
    // 닉네임 더블 체크 -------------------------------------------------------------
    selector = $('input[id="nick"]');
    if(!doubleCheck(selector)) {
      selector.focus();
      return false;
    }
    
    return true;
  }
  function passwdCheck(input){
    // 비밀번호가 같은지 체크
    if(input.val() != $('#passwd').val()){
      $('.necess.errMsg.' + input.attr("id")).text("비밀번호가 일치하지 않습니다.");
      $('.necess.errMsg.' + input.attr("id")).css("display", "block");
      
      console.log("passwdCheck 리턴 false");
      return false;
    }
    console.log("passwdCheck 리턴 true");
    return true;
  }
  function blankCheck(input){
    // 공란 체크
    if(input.val().trim().length == 0){
      $('.necess.errMsg.' + input.attr("id")).text("필수 입력 항목입니다.");
      $('.necess.errMsg.' + input.attr("id")).css("display", "block");
      
      console.log("blankCheck 리턴 false");
      return false;
    }
    console.log("blankCheck 리턴 true");
    return true;
  }
  function formatCheck(input){      
    let check = /[a-z|A-Z|0-9]{4,20}$/;
    
    // 닉네임 체크 -----------------------------------------------------------------
    if(input.attr("id") == "nick"){
      check = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣|a-z|A-Z|0-9]{2,10}$/;
      
      if(!check.test(input.val())){
        $('.necess.errMsg.' + input.attr("id")).text("2~10자의 한글, 영어 소문자 및 대문자 그리고 숫자만 입력 가능합니다.");
        $('.necess.errMsg.' + input.attr("id")).css("display", "block");
        return false;
      }
      
      return true;
    }
    // 전화번호 체크 ---------------------------------------------------------------
    if(input.attr("id") == "pNum"){
      check = /(^01[0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
      if(!check.test(input.val())){
        $('.necess.errMsg.' + input.attr("id")).text("형식에 맞지 않는 번호입니다.");
        $('.necess.errMsg.' + input.attr("id")).css("display", "block");
        
        return false;
      }
      input.val(input.val().replace(check, "$1-$2-$3"));
      
      return true;
    }
    
    // 이름 체크 ------------------------------------------------------------------
    if(input.attr("id") == "name"){
      check = /([ㄱ-ㅎㅏ-ㅣ가-힣]{2,5})$/;
      
      if(!check.test(input.val())){
        $('.necess.errMsg.' + input.attr("id")).text("2~5자의 한글만 입력 가능합니다.");
        $('.necess.errMsg.' + input.attr("id")).css("display", "block");
        return false;
      }
      
      return true;
    }
    
    // 아이디, 비밀번호, 비밀번호 확인 체크 ----------------------------------------------
    if(!check.test(input.val())){
      $('.necess.errMsg.' + input.attr("id")).text("4~20자의 영문 소문자 및 대문자 그리고 숫자만 사용 가능합니다.");
      $('.necess.errMsg.' + input.attr("id")).css("display", "block");
      return false;
    }
    
    console.log("formatCheck 리턴 true");
    return true;
  }
  
  $(document).ready(function(){
    
    // input 포커싱 이탈시 유효성 검사 및 에러 문구 출력 기능 ---------------------------------
    $("#uId").blur(function(){ 
      if(!doubleCheck($(this)) || !idCheckDone($(this))) 
        $('.necess.errMsg.' + $(this).attr("id")).css("display", "block"); 
    });
    $("#passwd").blur(function(){ doubleCheck($(this)); }); 
    $("#passCheck").blur(function(){ doubleCheck($(this)); });
    $("#name").blur(function(){ doubleCheck($(this)); });
    $("#pNum").blur(function(){ doubleCheck($(this)); });
    $("#nick").blur(function(){
      if(!doubleCheck($(this)) || !nickCheckDone($(this)))
        $('.necess.errMsg.' + $(this).attr("id")).css("display", "block"); 
    });
    // 회원 가입 ------------------------------------------------------------------
    //     data.msgID: 1(성공)/ 0(실패)
      $("#doInsert").on("click",function(){
        if(!lastInsertCheck()) return;
        
        switch(uCheckDone()){
          case '1':
            alert("아이디 중복확인을 해주세요."); 
            return;
            break;
          case '2':
            alert("닉네임 중복확인을 해주세요.");
            return;
            break;
          case '3':
            if(confirm("회원가입 하시겠습니까?")==false)return;
            break;
          default:
            alert("예상치 못한 에러 발생..");
            return;
            break;
        }
        
        let url = "${CP}/userinfo/doInsert.do";
        let method = "POST";
        let parameters = {
         "uId": $("#uId").val(),
         "passwd": $("#passwd").val(),
         "name": $("#name").val(),
         "pNum": $("#pNum").val(),
         "nick": $("#nick").val()
        };
        let async;
        
        EClass.callAjax(url,parameters,method,async,function(data){
          if("1"==data.msgId) alert(data.msgContents);
          else alert(data.msgContents);
                       
          window.location.href = "${CP}/login/doGetLogin.do?uId="+$("#uId").val()+"&passwd="+$("#passwd").val();
        });
      });
      // 회원가입 끝 --------------------------------------------------------------
        
      // id 중복 Check ----------------------------------------------------------
      $("#idCheck").on("click",function(){
        if(!doubleCheck($('input[id="uId"]'))) { $("#uId").focus(); return; }
        
        let url = "${CP}/userinfo/idCheck.do";
        let method ="GET";
        let async  = true;
        let parameters = { "uId": $("#uId").val() };
        
        EClass.callAjax(url, parameters, method, async, function(data) {
          if("1" == data.msgId){
            alert(data.msgContents); 
            //사용할수 없음
            $("#idCheckYN").val("0");
          }else{//id사용 가능
            alert(data.msgContents); 
            //사용할수 있음
            $("#idCheckYN").val("1");                   
          }
        });
      }); 
      // id 중복 Check 끝 ---------------------------------------------------------
          
          
      // 닉네임 중복 Check ---------------------------------------------------------
      $("#nickCheck").on("click",function(){
        if(!doubleCheck($('input[id="nick"]'))) { $("#nick").focus(); return; }
        
        let url = "${CP}/userinfo/nickCheck.do";
        let method ="GET";
        let async  = true;
        
        let parameters = { "nick": $("#nick").val() };
        
        EClass.callAjax(url, parameters, method, async, function(data) {
           console.log('data:'+data);
           if("1" == data.msgId){
            alert(data.msgContents); 
            //사용할수 없음
            $("#nickCheckYN").val("0");
           }else{//id사용 가능
            alert(data.msgContents); 
            //사용할수 있음
            $("#nickCheckYN").val("1");                   
           }
        });
      }); 
      // 닉네임 중복 Check 끝 -------------------------------------------------------
  });
</script>
</head>
<body>
  <%@include file="header.jsp"%>
  <script type="text/javascript" src="${CP_RES}/js/header.js"></script>

  <!-- 내용 -->
  <div id="wrap">
    <div class="txtbox">
      <h1>회원가입</h1>
      <p class="necess">*는 필수 입력 항목입니다.</p>
      <!-- id : 중복 확인 검사, 유효성 검사 필요 idRegExp = /^[a-zA-z0-9]{4,20}$/; -->
      <div class="box">
        <input type="text" name="idCheckYN" id="idCheckYN" value="0">
        <label for="uId">*아이디</label>
        <input id="uId" type="text" placeholder="아이디를 입력하세요."
          required="required" maxlength="20" />
        <input type="button" value="중복확인" id="idCheck" name="idCheck" />
        <p class="necess errMsg uId">4~20자의 영문 소문자 및 대문자 그리고 숫자만 사용
          가능합니다.</p>
      </div>
      <!-- id idRegExp = /^[a-zA-z0-9]{4,20}$/; ------------------------------------------------------->

      <!-- passwd /^[a-zA-z0-9]{4,20}$/; -->
      <div class="box">
        <label for="passwd">*비밀번호</label>
        <input name="passwd" id="passwd" type="password" required="required" 
          placeholder="비밀번호를 입력하세요." autocomplete="off" maxlength="20" />
        <p class="necess errMsg passwd" id="pw1">4~20자의 영문 소문자 및 대문자 그리고
          숫자만 사용 가능합니다.</p>
      </div>
      <!-- passwd var  --------------------------------------------------->

      <!-- passCheck-->
      <div class="box">
        <label for="passCheck">*비밀번호 확인</label>
        <input id="passCheck" type="password" placeholder="비밀번호를 다시 입력하세요."
          required="required" autocomplete="off" maxlength="20" />
        <p class="necess errMsg passCheck" id="pw2">비밀번호가 일치하지 않습니다.</p>
      </div>
      <!-- passCheck ---------------------------------------------->

      <!-- name /^[가-힣]{2,5}$/; -->
      <div class="box">
        <label for="name">*이름</label>
        <input id="name" type="text" placeholder="이름을 입력하세요." 
          required="required" maxlength="5" />
        <p class="necess errMsg name">2~5자의 한글만 입력 가능합니다.</p>
      </div>
      <!-- name ----------------------------------------------------->

      <!-- pNum  -->
      <div class="box">
        <label for="pNum">*휴대폰번호</label>
        <input id="pNum" type="text" placeholder="ex) 01012341234" 
          required="required" maxlength="13" />
        <p class="necess errMsg pNum">형식에 맞지 않는 번호입니다.</p>
      </div>
      <!-- pNum ----------------------------------------------------->

      <!-- nick : ^[a-zA-Z0-9]*$/ 20BYTE /로그인 시 아이디가 아닌 닉네임으로 보여줌-->
      <div class="box">
        <label for="nick">*닉네임</label>
        <input type="hidden" name="nickCheckYN" id="nickCheckYN" value="0">
        <input id="nick" type="text" placeholder="닉네임을 입력하세요." 
          required="required" maxlength="10" />
        <input type="button" value="중복확인" id="nickCheck" name="nickCheck"
          onclick="nickCheck" />
        <p class="necess errMsg nick">2~10자의 한글, 영어 소문자 및 대문자 그리고 숫자만 입력
          가능합니다.</p>
      </div>
      <!-- nick ------------------------------------------------->

      <!-- button -->
      <form action="${CP}/userinfo/doInsert.do" method="post" id="signFrm">
        <div class="gaib">
          <input type="button" value="회원가입" id="doInsert" name="doInsert">
        </div>
      </form>
      <!-- button --------------------------------------------------->
    </div>
  </div>

  <%@include file="footer.jsp"%>
  <!-- 내용 ----------------------------------------------------------->
</body>
</html>