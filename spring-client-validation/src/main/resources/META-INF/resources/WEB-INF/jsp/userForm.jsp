<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <link rel="stylesheet" href="https://jqueryvalidation.org/files/demo/site-demos.css">
    </head>
    <body>
        <h3>Welcome, Enter The User Details</h3>
        <form:form method="POST" id="userForm" action="/user" modelAttribute="user">
             <table>
                <tr>
                    <td><form:label path="name">Name</form:label></td>
                    <!--FIXME replace me with a tag-->
                    <td><input name="name" <c:forEach items="${validationRules['name']}" var="rule">
                                                ${rule.name}=${rule.value}
                                           </c:forEach>/>
                    </td>
                </tr>
                <tr>
                    <td><form:label path="age">Age</form:label></td>
                    <td><input name="age" <c:forEach items="${validationRules['age']}" var="rule">
                                                   ${rule.name}=${rule.value}
                                                </c:forEach> />
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
            </table>
        </form:form>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/additional-methods.min.js"></script>
        <script src="/js/user.js"></script>

</body>
</html>