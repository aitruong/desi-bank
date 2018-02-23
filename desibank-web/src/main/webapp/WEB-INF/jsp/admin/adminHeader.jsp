<div id="header">
   <img
	src="<%=request.getContextPath() %>/images/admin-header.jpg" width="770"
	height="200"></img>
	<br><marquee   align="center"  behavior="alternate"
			style="COLOR: #FF5050; font-family:Verdana; font-weight:bold"  scrollDelay=100  width=700
			bgColor=#FFFFFF height=16>Welcome To Online
		Desi@Bank </marquee></font> 
	</div>
	
	<div id="menu">
	<ul>
		<li><a href="${pageContext.request.contextPath}/admin/showUnapprovedCustomers.htm">Approve Customer</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/showUnLockedCustomers.htm">Unlock Customers</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/showCustomers.htm">Manage Customers</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/showLockedCustomers.htm?value=1">Lock Customer</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/changePassword.htm">Change Password</a></li>
		<li><a href="${pageContext.request.contextPath}/login.htm">Logout</a></li>
	</ul>
    </div>