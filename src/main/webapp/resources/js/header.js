/**
 * 
 */

  $("#header").mouseover(function(){
	  $("#header").css("background", "white");  
      $("#header a").css("color","#37385d");
      $(this).find('img').attr('src', '/kemie/resources/img/kemieLOGO.png');
      $("#header").css("transition", "all ease 1s 0s");
  });
  
  $("#header").mouseout(function(){
      $("#header").css("background", "#37385d");
      $("#header a").css("color","#ffffff");
      $(this).find('img').attr('src', '/kemie/resources/img/logo_white.png');
      $("#header").css("transition", "all ease 1s 0s");
  });