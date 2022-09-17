<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="endes.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<html>
<head>
<meta charset="UTF-8">
<title>Room詳細</title>
</head>
<body>

  <h1>Room詳細</h1>
  <%
    RoomBean room = (RoomBean)request.getAttribute("room");
    NumberOfPeopleBean numberOfPeople = (NumberOfPeopleBean)request.getAttribute("numberOfPeople");
  %>
    <div class="inf">
      <h2>[<%= room.getName() %>] </h2>
      <div class="inf-inner">
        <h4> RoomId: <%= room.getId() %> </h4>
        <h4>description: <%= roomBean.getDescription() %> </h4>
        <h4>現在この部屋には<big><big><%= numberOfPeople.getNumber() %></big></big>  人います。（<%= numberOfPeople.getTime() %>） </h4>
        <% if(numberOfPeople.getNumber >= 10){ %>
           <h3>人数が多いため入室できません</h3>
        <% } %><% else{ %>
           <h3>人数が少ないため入室できます</h3>
        <% } %>
      </div>
    </div>

  <p> <a href=><h3>部屋の人数遷移を見る</a></h3> </p>
  <p> <a href=><h3>部屋選択のページに戻る</a></h3> </p>

</body>
</html>

<style>
    .inf{
      padding: 10px 20px 20px;
      background-color: #bde0e4;
      box-shadow:1px 1px 4px;
    }
  
  .inf-inner{
      padding: 0 20px 0;
    }
  
  </style>