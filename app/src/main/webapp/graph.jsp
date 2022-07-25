<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sample</title>
</head><body>

<%
String [][] input = {
        <!-- set data from db  -->
        <!-- {CreatedAt, NumberOfPeople};-->
        {"10","4"}";
        {"20","6"};
        {"30","8"};
};
ArrayList<ArrayList<String>> ar1 = new ArrayList<ArrayList<String>>();
ArrayList<String> tmp = new ArrayList<String>();
for(int i=0; i<input.length; i++) {
        tmp = new ArrayList<String>();
        tmp.add(input[i][0]);
        tmp.add(input[i][1]);
        ar1.add(tmp);
}

session.setAttribute("chart1", ar1);
%>
<img src="CreateGraph" />
</body>
</html>