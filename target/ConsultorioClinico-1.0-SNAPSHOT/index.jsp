<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">


<%@include file="components/header.jsp"%>
<%@include file="components/bodyprimeraparte.jsp"%>

<div> 

    <!-- Usar JSTL para verificar si 'nombreUsuario' no está vacío -->
    <c:if test="${not empty nombreUsuario}">
        <p>Bienvenido, ${nombreUsuario}!</p>
    </c:if>
    
    <!-- Si 'nombreUsuario' está vacío -->
    <c:if test="${empty nombreUsuario}">
        <p>No hay usuario disponible.</p>
    </c:if>

</div>


  <%@include file="components/bodyfinal.jsp"%>         

   