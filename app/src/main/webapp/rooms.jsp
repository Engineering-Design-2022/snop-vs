<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="endes.*" %>


<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>部屋一覧</title>
  <link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">

</head>

<body>

  <h1>部屋一覧</h1>
  <p>登録された部屋の一覧</p>
  <section class="container">

    <%
    ArrayList<RoomBean> list = (ArrayList<RoomBean>)request.getAttribute("roomList");
    for (int i=0; i<list.size(); i++) {
      RoomBean roomBean = (RoomBean)list.get(i);
    %>
  
      <div class="card">
        <a href=<%= "room?id=" + roomBean.getId() %>>
          <h2> <%= roomBean.getName() %> </h2>
        </a>
        <p> <%= roomBean.getDescription() %> </p>
      </div>

    <%
    }
    %>

  </section>
</body>

</html>

<style>
  .container > * + * {
    margin-top: 2rem;
  }
  
  .card {
    padding: 4px 20px;
    background-color: #bde0e4;
    box-shadow: 1px 1px 4px;
  }
</style>