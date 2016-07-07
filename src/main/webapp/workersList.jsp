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
 <br>
    <form method="get" action="addworker">
        <input type="hidden" name="idDepartment" value=${param.idDepartment}></input>
        <input type="hidden" name="nameDepartment" value=${param.nameDepartment}></input>
        <input type="submit" name="submit" value="Add new worker"/>
    </form>
 <br>
 <br>
${message}
 <br>
 <table>
    <tr>
        <th>id</th>
        <th>Name</th>
        <th>birthday</th>
        <th>email</th>
        <th>department</th>
    </tr>

    <c:forEach items="${workers}" var="worker">
        <tr>
            <td>${worker.id}</td>
            <td>${worker.name}</td>
            <td>${worker.birthdey}</td>
            <td>${worker.email}</td>
            <td>${worker.department.name}</td>
            <td>
                 <form method="get" action="updateworker">
                     <input type="hidden" name="idWorker" value=${worker.id} />
                     <input type="hidden" name="nameWorker" value=${worker.name} />
                     <input type="hidden" name="birthdayWorker" value=${worker.birthdey} />
                     <input type="hidden" name="emailWorker" value=${worker.email} />
                     <input type="hidden" name="nameDepartmentWorker" value=${worker.department.name} />
                     <input type="hidden" name="idDepartment" value=${param.idDepartment}></input>
                     <input type="hidden" name="nameDepartment" value=${param.nameDepartment}></input>
                     <input type="submit" name="submit" value="Update worker"/>
                 </form>
            </td>
            <td>
                 <form method="post" action="deleteworker?idWorker=${worker.id}">
                      <input type="hidden" name="idDepartment" value=${param.idDepartment}></input>
                      <input type="hidden" name="nameDepartment" value=${param.nameDepartment}></input>
                      <input type="submit" name="submit" value="Delete"/>
                 </form>
            </td>
            <td>
                 <form method="get" action="departmentslist">
                      <input type="hidden" name="idWorker" value=${worker.id} />
                      <input type="hidden" name="nameWorker" value=${worker.name} />
                      <input type="submit" name="submit" value="Department"/>
                 </form>
            </td>
        </tr>
    </c:forEach>
 </table>

</body>
</html>