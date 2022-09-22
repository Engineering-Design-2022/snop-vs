<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="endes.*" %>
<%
    RoomBean room = (RoomBean)request.getAttribute("room");
    NumberOfPeopleBean numberOfPeople = (NumberOfPeopleBean)request.getAttribute("numberOfPeople");
%>

<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>部屋詳細</title>
  <link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
</head>

<body>
  <div class="container">

    <h1>部屋詳細</h1>

    <div class="inf">
      <h2> <%= room.getName() %> </h2>
      <div class="inf-inner">
        <p> <%= room.getDescription() %> </p>
        <h4>この部屋には<span class="num"> <%= numberOfPeople.getNumber() %> </span>人います。</h4>
        <p>（更新：<%= numberOfPeople.getCreatedAt() %>）</p>
        <a class="history-link" href=<%= "roomhistory?id=" + room.getId() %> >この部屋の人数遷移を見る</a>
      </div>
    </div>

    <div class="links">
      <a href="rooms">
        部屋選択のページに戻る
      </a>
    </div>

  </div>
</body>

</html>

<style>
  .container {
    min-height: 100vh;
  }

  .container>*+* {
    margin-top: 2rem;
  }

  .inf {
    padding: 10px 20px 20px 20px;
    background-color: #bde0e4;
    box-shadow: 1px 1px 4px;
  }

  .inf-inner {
    padding: 0 10px 0;
  }

  .history-link {
    margin-bottom: 20px;
  }

  .history-link:hover {
    cursor: pointer;
  }

  .num {
    margin-left: 1rem;
    margin-right: 1rem;
    font-size: 4rem;
  }
</style>