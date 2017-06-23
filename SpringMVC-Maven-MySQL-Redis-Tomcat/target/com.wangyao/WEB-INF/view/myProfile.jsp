<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" import="java.util.*" %>
<!DOCTYPE html>
<html>
<body>
<h2>Hello World!</h2>

<div class="user-info">
    <div class="user-uid">${user}</div>
    <div class="user-uid">${user.uid}</div>
    <div class="user-name">${user.username}</div>
</div>

</body>
</html>
