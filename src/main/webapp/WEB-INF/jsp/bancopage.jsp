<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Bancos</h1>
        <form method="POST" action="<c:url value="/web/bancos/add"/>">
            <table>
                <tbody>
                    <tr>
                        <td>Nombre del banco</td>
                        <td><input name="sbanco" value="${sbanco}"/>
                            <input type="hidden" name="idBanco" value="${idBanco}"></td>
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
                        <td colspan="2"><input type="button" onclick="document.location = '<c:url value="/web/bancos/clean"/>'" name="cancelar" value="cancelar"/></td>
                    </tr>
                </tbody> 

            </table>
        </form>
        <br/><br/><br/>
        <form method="GET" action="<c:url value="/web/bancos/getall"/>">
            <input name="sbanco"/>
            <input type="submit" name="Buscar" value="Buscar"/>
        </form>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Banco</th>
                    <th colspan="2"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${bancos}" var="item">
                    <tr>
                        <td><c:out value="${item.idBanco}"/></td>
                        <td><c:out value="${item.sbanco}"/></td>
                        <td><a href="<c:url value="/web/bancos/get/${item.idBanco}"/>">Edit</a></td>
                        <td><a href="<c:url value="/web/bancos/delete/${item.idBanco}"/>">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <c:if test="${empty bancos}">
            No hay bancos en la lista
        </c:if>
    </body>
</html>
