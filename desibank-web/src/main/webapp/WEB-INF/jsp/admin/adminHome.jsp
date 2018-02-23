<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Customer Home Page</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="<%=request.getContextPath() %>/css/desibank.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/css/style.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="adminHeader.jsp" />	
<div id="content">
<div id="right" style="width: 770px; height: 360px">&nbsp;
  	<table border="0" width="813" height="300" style="border-collapse: collapse">
		
<td height="445" width="532" valign="top">
			<p align="center">
			<img border="0" src="${pageContext.request.contextPath}/images/money.jpg" width="492" height="300"></td>
		
			<td height="445" width="330" valign="top">&nbsp;
<marquee  scrollamount="2" direction="up" loop="true" width="100%">			
<h3 align="left" ><font color="white" face="Trebuchet MS">			
<b><p>1. <u>Desi Bank</u> offers wide variety of Loans Products to suit your requirements.Coupled with convenience of networked branches/ ATMs and facility of E-channels like Internet and Mobile Banking, <p/><p>2. <u>Desi Bank</u> brings banking at your doorstep. Select any of our loan product and provide your details online and our representative will contact you for getting loans.
Home Loans
The No. 1 Home Loans Provider in the country, </p><p>3. <u>Desi Bank</u> Home Loans offers some unbeatable benefits to its customers - Doorstep Service, Simplified Documentation and Guidance throughout the Process. It's really easy ! </p>
</b></font></h3></marquee>
		</tr>
	</table>
  
</div>
</div>
<div id="footer">
<p>Copyright ? 2006 Sitename.com. Designed by <a
	href="http://www.freecsstemplates.org" class="link1">Free CSS
Templates</a> | Downloaded from <a href="http://www.cssbank.com/"><strong>Free
CSS Resource Bank</strong></a></p>
</div>
</body>
</html>