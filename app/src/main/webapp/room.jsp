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
    <div>
      <h2><%= room.getName() %> </h2>
      <h2> RoomId: <%= room.getId() %> </h2>
      <p> <%= numberOfPeople.getNumber() %> 人います </p>
    </div>

</body>
</html>