<%@ taglib prefix="spring"
	uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<!-- Head -->
<head>
<title>Desi Bank</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8">
<meta name="keywords"
	content="Corporate Bank a Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<!-- .css files -->
<link href="css/bars.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	type="text/css" media="all" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/nstyle.css"
	type="text/css" media="all" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/font-awesome.css" />
<!-- //.css files -->
<!-- Default-JavaScript-File -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<!-- //Default-JavaScript-File -->
<!-- fonts -->
<link
	href="//fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i&amp;subset=cyrillic,cyrillic-ext,greek,greek-ext,latin-ext,vietnamese"
	rel="stylesheet">
<link
	href="//fonts.googleapis.com/css?family=Ropa+Sans:400,400i&amp;subset=latin-ext"
	rel="stylesheet">
<!-- //fonts -->
<!-- scrolling script -->
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event) {
			event.preventDefault();
			$('html,body').animate({
				scrollTop : $(this.hash).offset().top
			}, 1000);
		});
	});
</script>
<!-- //scrolling script -->
</head>
<!-- //Head -->
<!-- Body -->
<body>
	<jsp:include page="top-bar.jsp" />
	<!-- Top-Bar -->


	<section class="blog" id="blog" style="margin-bottom: 20px;">
		<div class="container">
			<div class="blog-heading">
				<h3 style="text-align: left; font-size: 20px; display: inline;">Customer
					Registration Page</h3>
				&nbsp;&nbsp;&nbsp;&nbsp; <img alt=""
					src="${pageContext.request.contextPath}/images/add-payee.png"
					style="height: 80px;" /> <br /> <br />
					
					<span style="font-size: 16px;color:red;">${msg}</span>
				<%-- <h4 style="text-align: left; font-size: 20px; display: inline;">
					&nbsp;&nbsp;Name :&nbsp;&nbsp;&nbsp;&nbsp;<b>${customerSavingForm.name}</b>
				</h4>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<h4 style="text-align: left; font-size: 20px; display: inline;">&nbsp;&nbsp;Email
					:&nbsp;&nbsp;&nbsp;&nbsp;${customerSavingForm.email}</h4>
				<h4 style="text-align: left; font-size: 20px; display: inline;">&nbsp;&nbsp;Mobile
					:&nbsp;&nbsp;&nbsp;&nbsp; ${customerSavingForm.mobile}</h4>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<h4 style="text-align: left; font-size: 20px; display: inline;">&nbsp;&nbsp;Address
					:&nbsp;&nbsp;&nbsp;&nbsp;${customerSavingForm.location}</h4>
				<hr /> --%>
				<%-- <spring:form method="POST"
					action="${pageContext.request.contextPath}/customer/registration.htm"> --%>
				<spring:form method="POST"
					action="${pageContext.request.contextPath}/saving/accounts/registration.htm"
					enctype="multipart/form-data">
					<table class="table" style="width: 80%;">

						<tr>
							<td>UserID</td>
							<td><input type="text" id="userid" name="userid"
								class="form-control"
								style="background-color: #d9edf7; margin-left: 20px; width: 250px; color: black;"></td>
						
							<td>Date of Birth</td>
							<td><input type="date" value="" id="dob" name="dob"
								class="form-control"
								style="background-color: #d9edf7; margin-left: 20px; width: 250px; color: black;"></td>
						</tr>

						<tr>
							<td>Password</td>
							<td><input type="password" id="password" name="password"
								class="form-control"
								style="background-color: #d9edf7; margin-left: 20px; width: 250px; color: black;"></td>
						
							<td>Father's Name</td>
							<td><input type="text" id="father" name="father"
								class="form-control"
								style="background-color: #d9edf7; margin-left: 20px; width: 250px; color: black;"></td>
						</tr>

						<tr>
							<td>Name</td>
							<td><input type="text" value="${customerSavingForm.name}"
								id="name" name="name" class="form-control"
								style="background-color: #d9edf7; margin-left: 20px; width: 250px; color: black;"></td>
						
							<td>Qualification</td>
							<td><input type="text" id="qualification"
								name="qualification" class="form-control"
								style="background-color: #d9edf7; margin-left: 20px; width: 250px; color: black;"></td>
						</tr>

						<tr>
							<td>Address</td>
							<td><input type="text"
								value="${customerSavingForm.location}" id="address"
								name="address" class="form-control"
								style="background-color: #d9edf7; margin-left: 20px; width: 250px; color: black;"></td>
						
							<td>Job title</td>
							<td><input type="text" id="jobTitle" name="jobTitle"
								class="form-control"
								style="background-color: #d9edf7; margin-left: 20px; width: 250px; color: black;"></td>
						</tr>

						<tr>
							<td>Email</td>
							<td><input type="email" value="${customerSavingForm.email}"
								id="email" name="email" class="form-control"
								style="background-color: #d9edf7; margin-left: 20px; width: 250px; color: black;"
								readonly></td>
						
							<td>Photo</td>
							<td><input type="file" id="file" name="image"
								class="form-control"
								style="background-color: #d9edf7; margin-left: 20px; width: 250px; color: black;"></td>
						</tr>

						<tr>
							<td>Phone</td>
							<td><input type="tel" value="${customerSavingForm.mobile}"
								id="mobile" name="mobile" class="form-control"
								style="background-color: #d9edf7; margin-left: 20px; width: 250px; color: black;"></td>
						
							<td>Security Question 1</td>
							<td><select name="question1"
								style="margin-left: 20px; width: 250px;">
									<c:forEach items="${securityQuestions1}" var="question">
										<option value="${question.questions}">${question.questions}</option>


									</c:forEach>
							</select></td>
						</tr>

						<tr>
							<td>SSN</td>
							<td><input type="text" id="confirmPayeeAccountNo"
								name="ssn" class="form-control"
								style="background-color: #d9edf7; margin-left: 20px; width: 250px; color: black;"></td>
						
							<td>Security Answer 1:</td>
							<td><input type="text" value="" id="answer1" name="answer1"
								class="form-control"
								style="background-color: #d9edf7; margin-left: 20px; width: 250px; color: black;"></td>
						</tr>


						<tr>
							<td>Age</td>
							<td><input type="text" value="" id="age" name="age"
								class="form-control"
								style="background-color: #d9edf7; margin-left: 20px; width: 250px; color: black;"></td>
						
							<td>Security Question 2</td>
							<td><select name="question2"
								style="margin-left: 20px; width: 250px;">
									<c:forEach items="${securityQuestions2}" var="question">
										<option value="${question.questions}">${question.questions}</option>
									</c:forEach>
							</select></td>

						</tr>





						<tr>
							<td>GENDER</td>
							<td><select id="gender" name="gender" class="form-control"
								style="background-color: #d9edf7; margin-left: 20px; width: 250px; color: black;">
									<option>Male</option>
									<option>Female</option>
							</select></td>
						
							<td>Security Answer 2:</td>
							<td><input type="text" value="" id="answer2" name="answer2"
								class="form-control"
								style="background-color: #d9edf7; margin-left: 20px; width: 250px; color: black;"></td>
						</tr>

						<tr>
							<td colspan="4" style="text-align: right;"><input
								type="submit" name="addPayee"
								style="color: #FFFFFF; background: #ffb900; border: 2px solid #ffb900; text-transform: uppercase; padding: .2em 1em; font-size: 1.3em; font-family: 'Ropa Sans', sans-serif;"
								value="Register" id="customerRegistration"></td>
						</tr>
						</tbody>
					</table>
				</spring:form>
			</div>
		</div>
	</section>



	<!-- modal -->
	<div class="modal about-modal fade" id="myModal" tabindex="-1"
		role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span class="span1" aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">
						Corporate<span> Bank</span>
					</h4>
				</div>
				<div class="modal-body">
					<div class="agileits-w3layouts-info">
						<img src="${pageContext.request.contextPath}/images/business.jpg"
							alt="" />
						<p>Duis venenatis, turpis eu bibendum porttitor, sapien quam
							ultricies tellus, ac rhoncus risus odio eget nunc. Pellentesque
							ac fermentum diam. Integer eu facilisis nunc, a iaculis felis.
							Pellentesque pellentesque tempor enim, in dapibus turpis
							porttitor quis. Suspendisse ultrices hendrerit massa. Nam id
							metus id tellus ultrices ullamcorper. Cras tempor massa luctus,
							varius lacus sit amet, blandit lorem. Duis auctor in tortor sed
							tristique. Proin sed finibus sem.</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- //modal -->


	<!-- copyright -->
	<jsp:include page="../common/copyright.jsp" />
	<!-- //copyright -->

	<script src="js/jarallax.js"></script>
	<script src="js/SmoothScroll.min.js"></script>
	<script type="text/javascript">
		/* init Jarallax */
		$('.jarallax').jarallax({
			speed : 0.5,
			imgWidth : 1366,
			imgHeight : 768
		})
	</script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/move-top.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/easing.js"></script>

	<!-- here stars scrolling icon -->
	<script type="text/javascript">
		$(document).ready(function() {
			/*
				var defaults = {
				containerID: 'toTop', // fading element id
				containerHoverID: 'toTopHover', // fading element hover id
				scrollSpeed: 1200,
				easingType: 'linear' 
				};
			 */

			$().UItoTop({
				easingType : 'easeOutQuart'
			});

		});
	</script>
	<!-- //here ends scrolling icon -->
	<script src="js/bars.js"></script>
	<script type="text/javascript">
		function InvalidMsg(textbox) {
			if (textbox.length == 0) {
				textbox.setCustomValidity('please enter your userid.');
			} else {
				textbox.setCustomValidity('');
			}
			return true;
		}
	</script>


</body>
<!-- //Body -->
</html>
<!-- //html -->