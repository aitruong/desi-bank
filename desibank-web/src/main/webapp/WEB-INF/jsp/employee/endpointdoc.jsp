<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<!-- Head -->
<head>
<title>Desi Bank</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8">
<meta name="keywords" content="Corporate Bank a Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<!-- .css files -->
	<link href="${pageContext.request.contextPath}/css/bars.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" media="all" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/nstyle.css" type="text/css" media="all" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.css" />
<!-- //.css files -->
<!-- Default-JavaScript-File -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	</head>
<body>

<div class="container">
  <div class="container">
    <h1>Spring MVC 3.1 Demo Endpoints</h1>
    <c:forEach items="${handlerMethods}" var="entry">
      <div>
        <hr>
        <p><strong>${entry.value}</strong></p>      
      </div>
      <div class="span-3 colborder">
        <p>
          <span class="alt">Patterns:</span><br> 
          <c:if test="${not empty entry.key.patternsCondition.patterns}">
            ${entry.key.patternsCondition.patterns}
          </c:if>
        </p>
      </div>
      </c:forEach>
      </div>
      </div>

</body>
</html>