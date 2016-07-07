<%--
    Document   : addworkers
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
${message}
<br>
<br>
<form method="post" action="updateworker">
    <table>
       <tr>
          <th>Id</th>
          <th>Name</th>
          <th>birthday</th>
          <th>email</th>
          <th>department</th>
       </tr>
       <tr>
          <td>
             <input type="text" name="idWorker" readonly value=${param.idWorker} />
          </td>
          <td>
             <input type="text" name="nameWorker" value=${param.nameWorker} ></input>
          </td>
          <td>
             <input type="date" name="birthdayWorker" value=${param.birthdayWorker} ></input>
          </td>
          <td>
             <input type="text" name="emailWorker" value=${param.emailWorker} ></input>
          </td>
          <td>
             <select name="nameDepartmentWorker">
                 <option selected="selected" value=${param.nameDepartmentWorker}> ${param.nameDepartmentWorker} </option>
                 <c:forEach items="${departments}" var="department">
                         <option value=${department.name}> ${department.name} </option>
                 </c:forEach>
             </select>
          </td>
          <td>
             <input type="hidden" name="idDepartment" value=${param.idDepartment}></input>
             <input type="hidden" name="nameDepartment" value=${param.nameDepartment}></input>
             <input type="submit" name="submit" value="Update"/>
          </td>
    </table>
</form>
</body>
</html>