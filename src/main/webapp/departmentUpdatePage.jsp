<%--
    Document   : addworkers
    Created on : 27.06.2016, 15:29:10
    Author     : wanderbe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
  <form method="post" action="updatedepartment">
    <table>
       <tr>
          <th>Id</th>
          <th>Name of Department</th>
       </tr>
       <tr>
          <td>
             <input type="text" name="idDepartment" readonly value=${param.idDepartment} />
          </td>
          <td>
             <input type="text" name="nameDepartment" value=${param.nameDepartment} />
          </td>
          <td>
             <input type="submit" name="submit" value="Update department"/>
          </td>
       </tr>
    </table>
  </form>
</body>
</html>