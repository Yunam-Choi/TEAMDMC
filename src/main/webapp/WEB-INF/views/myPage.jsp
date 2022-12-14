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
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
    <!-- jQuery cdn -->
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        
        
     <!--   let settings = {
                  "async": true,
                  "crossDomain": true,
                  "url": "https://api.upbit.com/v1/trades/ticks?market=%20KRW-BTC&count=1",
                  "method": "GET",
                  "headers": {
                    "Accept": "application/json"
                  }
                };
            $.ajax(settings).done(function (response) {
              console.log(response);
              let htmlData = "";
          
                  //htmlData += "<tr>                                                                                       ";
                 // htmlData += " <td class='text-center col-sm-1 col-md-1 col-lg-1'>"+ response.trade_date_utc +"</td>      ";
                 // htmlData += "<tr>                                                                                       ";
                 // htmlData += " <td class='text-left   col-sm-2 col-md-2 col-lg-2'>"+ response.trade_time_utc +"</td> ";
                 // htmlData += " <td class='text-left   col-sm-2 col-md-2 col-lg-2'>"+ response.trade_price +"</td>";
                 // htmlData += "<tr>                                                                                       ";
                  
                  $("#myCoin").append(htmlData);
                  
              }); */
              -->
            
<style>
@import
    url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap')
    ;
</style>
<link rel="stylesheet" type="text/css" href="${CP_RES }/css/header.css">
<link rel="stylesheet" type="text/css" href="${CP_RES }/css/footer.css">
<!-- ????????? ?????? -->
<style type="text/css">
/* ????????? ?????? */
/* ??? ?????? ??? margin ??? */
* {
    margin: 0;
    padding: 0;
    font-family: 'Noto Sans KR', sans-serif;
}
/* ?????? container div */
.container {
    width: 100%;
    height: calc(100vh - 80px);
    position: relative;
    background: white;
    /* padding top> right> bottom> left */
}
.box {
    width: 90%;
    height: auto;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
}
.page-header {
    width: 100%;
    text-align: center;
    margin-bottom: 50px;
}
.page-header h1 {
    font-size: 45px;
    font-weight: border;
    border-bottom: 1px solid lightgray;
    padding-bottom: 20px;
}
#wrap {
    margin: 0 auto;
    width: 100%;
    height: auto;
    justify-content: space-between;
}
/* ?????? ?????? ?????? div */
#userInfoDiv {
    width: 100%;
    height: 180px;
    padding: 15px 0 0 30px;
    box-sizing: border-box;
    border-bottom: 1px solid lightgray;
}
/* ??? ?????? div */
#userNewsDiv {
    width: 100%;
    height: 150px;
    margin-top: 20px;
    border-bottom: 1px solid lightgray;
    box-sizing : border-box;
    padding: 15px 15px 0px 30px;
    box-sizing: border-box;
}
#userInfoDiv .btn {
    color: #fffafa;
    border-radius: 2px;
    width: 130px;
    height: 40px;
    line-height: 30px;
    background: #37385d;
    margin-top: 15px;
}
.userNewsTime {float: right;}
h3 {
    margin-bottom: 20px;
}
.btbox {width: 600px; display: flex; justify-content: space-between;}
</style>

<title>KEMIE</title>
<!-- reset.css??? ?????????????????? ???????????? 
            <link rel="stylesheet" type="text/css" href="/studyhtml5/asset/css/reset.css"> -->

<!-- jQuery cdn -->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

<!-- ?????????????????? favicon?????? -->
<link rel="shortcut icon" type="image/x-icon" href="/studyhtml5/favicon.ico">

<!-- ????????? ?????? function, ISEmpty -->
<script src="${CP_RES}/js/eUtil.js"></script>
<!-- ????????? ??????  function, callAjax -->
<script src="${CP_RES}/js/eclass.js"></script>

<!-- ?????????????????? -->
<script type="text/javascript">
            /* ?????????????????? ?????? */
            $(document).ready(function(){
                console.log('PCWK *** document');
                
                function myBoard() {
                    window.location.href = "${CP}/board/boardView.do?searchDiv=50&searchWord=${sessionScope.user.uId}";
                }
                
                $("#myPost").on("click", function(){
                    myBoard();
                });
             
                
                function getByteLength(s, b, i, c){
                    for(b=i=0;c=s.charCodeAt(i++);b+=c>>11?3:c>>7?2:1);
                    return b;
                }
                
                $("button[id*='Change']").click(function(){
                    let btnId = $(this).attr("id");
                    
                    if(btnId.includes('nick')){
                        let uId = 'id01';
                        let input = prompt('????????? ???????????? ???????????????.(?????? 2~10???)');
                        let inputLength = getByteLength(input);
                        
                        if(inputLength < 6 || inputLength >= 30) alert('???????????? ??????2???, ?????? 10????????????!');
                        else {
                            let url = "${CP}/nickCheck.do";
                            let parameters = {"nick" : input};
                            let method = "GET";
                            let async;
                            
                            EClass.callAjax(url, parameters, method, async, function(data) {                                
                                if(data.msgId == 0){   
                                    if(confirm(data.msgContents+"?????????????????????????")==true){
                                        url = "${CP}/doNickUpdate.do";
                                        parameters = {"uId": uId, "nick": input};
                                        
                                        EClass.callAjax(url, parameters, method, async, function(upData) {
                                            alert(upData.msgContents);
                                        });
                                    }
                                }else{
                                    alert(data.msgContents);
                                }
                            });
                        }
                    } else if(btnId.includes('pw')){
                            let input = prompt('????????? ??????????????? ???????????????.');
                            
                            let url = "${CP}/userinfo/doPassUpdate.do";
                let parameters = {"passwd": input, "uId" : "${sessionScope.user.uId}" };
                let method = "POST";
                let async;
                            
                EClass.callAjax(url, parameters, method, async, function(data) {
                    alert(data.msgContents);
                });
                }else if(btnId.includes('user')){
                      if(confirm("?????? ?????????????????????????")==false) return;
                      let input = prompt('???????????? ???????????? ?????? ?????? ??????????????? ???????????????.');
                      if(eUtil.ISEmpty(input)) return;
                      
                      let url = "${CP}/userinfo/doDelete.do";
                      let parameters = {"uId": "${sessionScope.user.uId}", "passwd": input};
                      let method= "POST";
                      let async;
                      
                      EClass.callAjax( url, parameters, method, async, function(data) {
                          alert(data.msgContents);
                          
                          window.location.href = "${CP}/login/doLogout.do";
                      });
                }
            });
      });
        </script>
</head>
<body>
    <!-- header --------------------------------------------------------------->
    <%@include file="header.jsp" %>
    <script type="text/javascript" src="${CP_RES }/js/header.js"></script>
    <!-- header end ----------------------------------------------------------->
    <!-- div container -------------------------------------------------------->
    <div class="container">

        <div class="box">
            <!-- mainTitle ---------------------------------------------------------->
            <div class="page-header" id="mainTitle">
                <h1>???????????????</h1>
            </div>
            <!-- mainTitle end ------------------------------------------------------>
            <!-- wrapDiv ------------------------------------------------------------>
            <div id="wrap">
                <!-- userInfoDiv -------------------------------------------------------->
                <div id="userInfoDiv">
                    <h3>?????? ?????? ??????</h3>
                    <p class="userInfo_Name">${sessionScope.user.nick} ???</p>
    
                    <div class="btbox">
                            <!-- Button ----------------------------------------------------------->
                            <button id="nickChange" class="btn nickChange">????????? ??????</button>
                            <button id="pwChange" class="btn pwChange">???????????? ??????</button>
                            <button id="userChange" class="btn userChange">?????? ??????</button>
                            <button id="myPost" class="btn myPost" >??? ??? ??????</button>
                            <!--// Button end ----------------------------------------------------->
                    </div>
                </div>
                <!--// userInfoDiv end -------------------------------------------------->

                <!-- userNewsDiv -------------------------------------------------------->
                <div id="userNewsDiv">
                    <h3>??? ??????</h3>
                    <!-- ?????????: 1. ?????? ????????? ?????? ????????? ?????? ?????????.. ????????? x...
                           ex) ???????????? <<12.3>>?????? ?????? ???????????????.
                           
                           2. ????????? ????????? ????????? ?????? ????????????... ?????? ?????? ????????? ?????? ??????,,, ??? ??? ????????? ?????? ??????..?
                -->
                    <label class="userNewsContext">?????? ?????? ?????? - ???????????? ????????? ?????????????????????.</label> 
                    <label class="userNewsTime">2022.07.12 12:30</label><br /> 
                    <label class="userNewsContext">?????? ?????? ?????? - ??????????????? ????????? ?????????????????????.</label> 
                    <label class="userNewsTime">2022.07.12 12:26</label><br />
                </div>
                <!--// userNewsDiv end -------------------------------------------------->
            </div>
            <!--// wrap end ----------------------------------------------------------->
        </div>
    <!--// div container end -------------------------------------------------->
    </div>
    <!-- footer ------------------------------------------------------------->
        <%@include file="footer.jsp" %>
        <!-- footer end --------------------------------------------------------->
    
</body>
</html>