<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="endes.*" %>
<% RoomBean room = (RoomBean)request.getAttribute("room"); %>

<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>人数遷移の履歴</title>
  <link rel="stylesheet" href="https://unpkg.com/sakura.css/css/sakura.css" type="text/css">
</head>

<body>
  <div class="container">
    <div>
      <a href=<%= "room?id=" + room.getId() %> >
        <h1><%= room.getName() %></h1>
      </a>
      <p>人数の履歴</p>
    </div>

    <div class="container">
      <%
        ArrayList<NumberOfPeopleBean> list = (ArrayList<NumberOfPeopleBean>)request.getAttribute("numberOfPeopleList");
        for (int i=0; i<list.size(); i++) {
          NumberOfPeopleBean nBean = (NumberOfPeopleBean)list.get(i);
      %>
    
        <div class="card">
          <h3><span> <%= nBean.getNumber() %> </span>人 </h3>
          <p class="card-date"> <%= nBean.getCreatedAtString()  %> </p>
        </div>

      <%
        } 
      %>
    </div>
    
    <a href="rooms">
      部屋選択のページに戻る
    </a>
  </div>
</body>

</html>

<style>
  .container >*+*{
    margin-top: 2rem;
  }
  
  .card {
    padding-top: 8px;
    padding-bottom: 8px;
    padding-left: 40px;
    padding-right: 40px;
    background-color: #dcdcdc;
    box-shadow: 1px 1px 4px;
    margin-left: 10px;
    margin-right: 10px;
    margin-bottom: 20px;
    display: flex;
  }

  .card>h3>span {
    font-size: 5rem;
    margin-right: 1rem;
  }

  .card-date {
    margin: auto auto;
  }
</style>