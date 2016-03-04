<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Persons</h1>
        <form method="POST" action="<c:url value="/agenda/add"/>">
            <table>
                <tbody>
                    <tr>
                        <td>First name</td>
                        <td><input name="firstName" value="${firstname}"/>
                            <input type="hidden" name="id" value="${id}"></td>
                    </tr>
                    <tr>
                        <td>Last name</td>
                        <td><input name="lastName" value="${lastname}"/></td>
                    </tr>
                    <tr>
                        <td>Money</td>
                        <td><input name="money" value="${money}"/></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <c:choose>
                                <c:when test="${empty edit}">
                                    <input type="submit" name="aceptar" value="aceptar"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="submit" name="actualizar" value="actualizar"/>
                                </c:otherwise>
                            </c:choose> 

                        </td>
                        <td colspan="2"><input type="button" onclick="document.location = '../clean'" name="cancelar" value="cancelar"/></td>
                    </tr>
                </tbody> 

            </table>
        </form>
        <table>
            <thead>
                <tr>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Money</th>
                    <th colspan="4"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${persons}" var="person">
                    <tr>
                        <td><c:out value="${person.firstName}"/></td>
                        <td><c:out value="${person.lastName}"/></td>
                        <td><c:out value="${person.money}"/></td>
                        <td><a href="<c:url value="/agenda/get/${person.id}"/>">Edit</a></td>
                        <td><a href="<c:url value="/agenda/delete/${person.id}"/>">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <c:if test="${empty persons}">
            No hay personas en la lista <a href="${addUrl}">agrega persona</a>
        </c:if>
    </body>
</html>
