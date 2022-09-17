<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="endes.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<html>
<head>
<meta charset="UTF-8">
<title>Room一覧</title>
</head>
<body>

  <h1>Room一覧</h1>
  <h3>登録された部屋の一覧</h3>
  <%
   ArrayList<RoomBean> list = (ArrayList<RoomBean>)request.getAttribute("roomList");
   for (int i=0; i<list.size(); i++) {
    RoomBean roomBean = (RoomBean)list.get(i);
  %>
    <div class="card">
      <a href=<%= "room?id=" + roomBean.getId() %>><h2> <%= roomBean.getName() %> </h2></a>
      <p>Id: <%= roomBean.getId() %> </p>
      <p>description: <%= roomBean.getDescription() %> </p>
    </div>
  <%
  } 
  %>

  <p> <a href=><h3>部屋選択のページに戻る</a></h3> </p>

</body>
</html>

<style>
    .card{
      padding: 10px 20px 10px;
      background-color: #bde0e4;
      box-shadow:1px 1px 4px;
    }
  </style>