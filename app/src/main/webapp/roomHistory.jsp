<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="endes.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<html>
<head>
<meta charset="UTF-8">
<title>人数の履歴</title>
</head>
<body>
  <% RoomBean room = (RoomBean)request.getAttribute("room"); %>

  <h1><%= room.getName() %></h1>
  <%
   ArrayList<NumberOfPeopleBean> list = (ArrayList<NumberOfPeopleBean>)request.getAttribute("numberOfPeopleList");
   for (int i=0; i<list.size(); i++) {
    NumberOfPeopleBean nBean = (NumberOfPeopleBean)list.get(i);
  %>
    <div>
      <h2> <%= nBean.getNumber() %> 人 </h2>
      <p>日付: <%= nBean.getCreatedAt()  %> </p>
    </div>
  <%
  } 
  %>

</body>
</html>