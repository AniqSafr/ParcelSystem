<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Parcel Report</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
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

    <div class="container">
        <h1>Parcel Report</h1>

        <form action="<%=request.getContextPath()%>/report" method="GET">
            <div class="form-group">
                <label for="available">Enter Date:</label>
                <input type="date" id="available" name="available" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-primary">Get Count</button>
        </form>

        <%-- Display the count of parcels for the specified date if available --%>
        <c:if test="${not empty countAllDateIn}">
       		<p>All Parcel for Date: <b><c:out value="${param.available}" /></b> is <b><c:out value="${countAllDateIn}" /></b></p> 
            <p>Available parcels for Date:  <b><c:out value="${param.available}" /></b> is <b><c:out value="${countInDateIn}" /></b></p>
            <p>Parcel Check out for Date: <b><c:out value="${param.available}" /></b> is <b><c:out value="${countOutDateIn}" /></b></p> 
            <p>Profit :<b>RM <c:out value="${total}" /></b></p> <!-- Display the calculated money -->
        </c:if>

        <%-- Add additional content or formatting as needed --%>
    </div>

</body>
</html>
