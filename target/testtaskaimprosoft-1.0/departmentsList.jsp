<%--
    Document   : workerslist
    Created on : 27.06.2016, 15:29:10
    Author     : wanderbe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
<br>
<a href="departmentslist">Departments</a>
<a href="workerslist">Workers</a>
<br>
${param.message}
<br>
<form method="get" action="adddepartment">
     <input type="submit" name="submit" value="Add new department"/>
</form>
<br>
${message}
<br>
<table class="item-table">
    <tr>
        <th>id</th>
        <th>Name of department</th>
    </tr>

    <c:forEach items="${departments}" var="department">
        <tr>
            <td>${department.id}</td>
            <td>${department.name}</td>
            <td>
             <form method="get" action="updatedepartment">
                <input type="hidden" name="idDepartment" value=${department.id} />
                <input type="hidden" name="nameDepartment" value=${department.name} />
                <input type="submit" name="submit" value="Update"/>
                </form>
            </td>
            <td>
             <form method="post" action="deletedepartment?idDepartment=${department.id}">
                <input type="submit" name="Delete" value="Delete"/>
             </form>
            </td>
            <td>
             <form method="get" action="workerslist">
                <input type="hidden" name="idDepartment" value=${department.id} />
                <input type="hidden" name="nameDepartment" value=${department.name} />
                <input type="submit" name="submit" value="List of workers"/>
             </form>
            </td>
        </tr>
    </c:forEach>

</table>


</body>
</html>