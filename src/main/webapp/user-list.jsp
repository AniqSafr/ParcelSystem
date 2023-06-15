<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Parcel Management System</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark" style="background-color: black">
        <div>
            <a href="https://github.com/AniqSafr" class="navbar-brand">Parcel Management System</a>
        </div>

        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a href="<%=request.getContextPath()%>/list" class="nav-link">Parcel</a>
            </li>
            <li class="nav-item">
                <a href="<%=request.getContextPath()%>/available" class="nav-link">Available</a>
            </li>
            <li class="nav-item">
                <a href="<%=request.getContextPath()%>/report" class="nav-link">Parcel Report</a>
            </li>
        </ul>

        <ul class="navbar-nav">
            <li class="nav-item">
                <a href="<%=request.getContextPath()%>/logout" class="nav-link">Log Out</a>
            </li>
        </ul>
    </nav>

	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
		<div class="container">
			<h3 class="text-center">List of Parcel</h3>
			<hr>
			<div class="container text-left">
    <div class="row">
    <div class="col-md-6">
        <div class="text-left">
            <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add New Parcel</a>
        </div>
    </div>
    <div class="col-md-6">
        <div class="form-group">
            <form action="search" method="get">
                <div class="input-group">
                    <input type="text" name="dateIn" placeholder="Search by Date or Name" class="form-control">
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-primary">Search</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</div>

			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>NoPhone</th>
						<th>NoMatric</th>
						<th>DateIn</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="parcel" items="${listUser}">

						<tr>
							<td><c:out value="${parcel.id}" /></td>
							<td><c:out value="${parcel.name}" /></td>
							<td><c:out value="${parcel.noPhone}" /></td>
							<td><c:out value="${parcel.noMatric}" /></td>
							<td><c:out value="${parcel.dateIn}" /></td>
							<td><a href="edit?id=<c:out value='${parcel.id}' />">Check Out</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${parcel.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
		
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>