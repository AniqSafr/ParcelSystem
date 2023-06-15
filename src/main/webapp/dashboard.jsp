<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
if (session.getAttribute("matric") == null) {
	response.sendRedirect("login.jsp");
}
%>
<!DOCTYPE html>

<html>
<head>
    <title>Parcel Management System - Dashboard</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <style>
        body {
            background-color: #f8f9fa;
        }

        .container {
            margin-top: 50px;
            max-width: 600px;
        }

        h1 {
            text-align: center;
            color: #007bff;
            margin-bottom: 30px;
        }

        .logo {
            text-align: center;
            margin-bottom: 20px;
        }

        .logo img {
            width: 150px;
            height: auto;
        }

        .btn-primary,
        .btn-success {
            font-size: 20px;
            padding: 15px 30px;
            margin-bottom: 20px;
            box-shadow: 0px 3px 6px rgba(0, 0, 0, 0.1);
        }

        .btn-primary:hover,
        .btn-success:hover {
            transform: translateY(-2px);
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Parcel Management System - Dashboard</h1>
    <div class="logo">
        <img src="images/upm.png" alt="Logo">
    </div>
    <div class="row">
        <div class="col-md-6">
            <a href="<%= request.getContextPath() %>/list" class="btn btn-primary btn-block">All List</a>
        </div>
        <div class="col-md-6">
            <a href="<%= request.getContextPath() %>/available" class="btn btn-success btn-block">Available List</a>
        </div>
    </div>
</div>
</body>
</html>
