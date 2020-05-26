<html>
<body>
    <%@page import="com.patientService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<%
//Insert item---------------------------------
if (request.getParameter("pid") != null)
 {
 patient p = new patient();
 String stsMsg = p.insertPatient(request.getParameter("pid"),
 request.getParameter("pname"),
 request.getParameter("gender"),
 request.getParameter("address")
  request.getParameter("phone"));
 session.setAttribute("statusMsg", stsMsg);
 }
 
//Delete item----------------------------------

if (request.getParameter("itemID") != null)
 {
 patient p = new patient();
 String stsMsg = p.deletePatient(request.getParameter("pid"));
 session.setAttribute("statusMsg", stsMsg);
 }
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Patient Service</title>
</head>
<body>
<h1>Patient Service</h1>
<form method="post" action="patientService.jsp">
 Patient ID: <input name="pid" type="text"><br> Patient
 name: <input name="pname" type="text"><br> Gender:
 <input name="gender" type="text"><br> Address: 
 <input name="address" type="text"><br> Phone: 
 <input name="phone" type="text"><br><input
 name="btnSubmit" type="submit" value="Save">
</form>
<%
 out.print(session.getAttribute("statusMsg"));
%>
4
<br>


<%
 patient p = new patient();
 out.print(p.readPatient());
%>
</body>
</html>
</body>
</html>
