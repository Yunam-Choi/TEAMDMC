<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--html comment-->
<!DOCTYPE html>
<html>
<body>
    <div id="header">
        <div class="hdbox">
            <ul class="menu">
                <li class="logo"><a href="${CP}/mainPage.do"><img src="${CP_RES}/img/logo_white.png"></a></li>
                <li><a href="${CP}/exchange.do">거래소</a></li>
                <li><a href="${CP}/balancesPage.do">입출금</a></li>
                <li><a href="${CP}/board/boardView.do">커뮤니티</a></li>
                <li><a href="${CP}/faq.do">고객센터</a></li>
            </ul>
            <ul class="user">
                <c:choose>
                    <c:when test="${null !=sessionScope.user}">
                    <%-- sessionScope.user가 null이 아니면 => user에 값이 있으면 => 로그인이 됐다. --%>
                        <li><a href="${CP}/userinfo/myPage.do"><span>${sessionScope.user.nick}님</span></a></li>  
                        <li><a href="${CP}/login/doLogout.do"><span>로그아웃</span></a></li>
                    </c:when>
                    <c:otherwise>
                    <%-- 위의 if문 조건이 false => user에 값이 없는 경우  --%>
                      <li><a href="${CP}/login/loginView.do"><span>로그인</span></a></li>
                      <li><a href="${CP}/signin.do">회원가입</a></li>
                    </c:otherwise>
                </c:choose>

            </ul>
        </div>
    </div>
</body>
</html>