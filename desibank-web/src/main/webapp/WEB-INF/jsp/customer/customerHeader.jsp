<div id="header">
<img
	src="<%=request.getContextPath() %>/images/customerHead.jpg" width="100%"
	height="200"></img>
	<br><center style="font-size:20px; font-family: 'Arvo', serif; background:lightblue;"><b>Welcome To Online Desi@Bank </b></center>
	</div>
	
	<div id="menu">
	<ul>
		<li><a href="${pageContext.request.contextPath}/customer/showCustomerAccounts.htm">My Accounts</a></li>
		<li><a href="${pageContext.request.contextPath}/customer/transferMoney.htm">Fund Transfer</a></li>
		<li><a href="${pageContext.request.contextPath}/customer/showCustomerAccountsSummary.htm">Account Summary</a></li>
		<li><a href="${pageContext.request.contextPath}/customer/addPayee.htm">Add Payee</a></li>
		<li><a href="${pageContext.request.contextPath}/customer/registeredPayees.htm">Registered Payees</a></li>
		<li><a href="${pageContext.request.contextPath}/auth/logout.htm">logout</a></li>
		<li><a href="${pageContext.request.contextPath}/loan/loan.htm">Loan</a></li>
	</ul>
</div>