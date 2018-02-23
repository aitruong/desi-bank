<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
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
	<link href="css/bars.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" media="all" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/nstyle.css" type="text/css" media="all" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.css" />
<!-- //.css files -->
<!-- Default-JavaScript-File -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<!-- //Default-JavaScript-File -->
<!-- fonts -->
	<link href="//fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i&amp;subset=cyrillic,cyrillic-ext,greek,greek-ext,latin-ext,vietnamese" rel="stylesheet">
	<link href="//fonts.googleapis.com/css?family=Ropa+Sans:400,400i&amp;subset=latin-ext" rel="stylesheet">
<!-- //fonts -->
<!-- scrolling script -->
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event){		
			event.preventDefault();
			$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
		});
	});
</script>
<!-- //scrolling script -->
</head>
<!-- //Head -->
<!-- Body -->
<body>
		<jsp:include page="top-bar.jsp"/>
		<!-- Top-Bar -->
	
<jsp:include page="customer-menu.jsp"/>

<section class="blog" id="blog" style="margin-bottom: 20px;">
	<div class="container">
		<div class="blog-heading">
			<h3  style="text-align: left;font-size: 20px;display: inline;" >Add Payee Page</h3>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<img alt="" src="${pageContext.request.contextPath}/images/add-payee.png" style="height: 40px;"/>
			<hr/>
		<spring:form method="POST" 
					commandName="addpayee" 
							action="${pageContext.request.contextPath}/customer/confirmPayee.htm">
			<table class="table" style="width: 80%;">
      <tr>
      <td>Account Number</td>
		<td><input type="text"  id="payeeAccountNo" name="payeeAccountNo" class="form-control"  style="background-color: #d9edf7;margin-left: 20px;width: 350px;color:black;"></td>
      </tr>
      <tr>
      <td>Confirm Account Number</td>
		<td><input type="password"  id="confirmPayeeAccountNo" name="confirmPayeeAccountNo" class="form-control"  style="background-color: #d9edf7;margin-left: 20px;width: 350px;color:black;"></td>
      </tr>
      
        <tr>
      <td>Payee Name</td>
		<td><input type="text"  id="payeeName" name="payeeName" class="form-control"  style="background-color: #d9edf7;margin-left: 20px;width: 350px;color:black;"></td>
      </tr>
      
        <tr>
      <td>Payee Nick Name</td>
		<td><input type="text"  id="payeeNickName" name="payeeNickName" class="form-control"  style="background-color: #d9edf7;margin-left: 20px;width: 350px;color:black;"></td>
      </tr>
      
         <tr>
      <td>Alert will be sent to email id</td>
		<td><spring:input type="text"  path="email" class="form-control"  style="background-color: #d9edf7;margin-left: 20px;width: 350px;color:black;" readonly="true"/></td>
      </tr>
      
           <tr>
		<td colspan="2" style="text-align: right;">
				<button type="submit" class="btn btn-info" id="addPayee" name="addPayee">Add Payee</button>
		</td>
      </tr>
    </tbody>
  </table>			
  </spring:form>
  <br/>
		</div>
	</div>
</section>		

	

<!-- modal -->
	<div class="modal about-modal fade" id="myModal" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header"> 
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span class="span1"aria-hidden="true">&times;</span></button>						
						<h4 class="modal-title"> Corporate<span> Bank</span></h4>
					</div> 
					<div class="modal-body">
					<div class="agileits-w3layouts-info">
						<img src="${pageContext.request.contextPath}/images/business.jpg" alt="" />
						<p>Duis venenatis, turpis eu bibendum porttitor, sapien quam ultricies tellus, ac rhoncus risus odio eget nunc. Pellentesque ac fermentum diam. Integer eu facilisis nunc, a iaculis felis. Pellentesque pellentesque tempor enim, in dapibus turpis porttitor quis. Suspendisse ultrices hendrerit massa. Nam id metus id tellus ultrices ullamcorper.  Cras tempor massa luctus, varius lacus sit amet, blandit lorem. Duis auctor in tortor sed tristique. Proin sed finibus sem.</p>
					</div>
				</div>
			</div>
		</div>
	</div>
<!-- //modal -->
	
<!-- copyright -->
<jsp:include page="../common/copyright.jsp"/>
<!-- //copyright -->

	<script src="js/jarallax.js"></script>
	<script src="js/SmoothScroll.min.js"></script>
	<script type="text/javascript">
		/* init Jarallax */
		$('.jarallax').jarallax({
			speed: 0.5,
			imgWidth: 1366,
			imgHeight: 768
		})
	</script>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/move-top.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/easing.js"></script>
	
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
								
			$().UItoTop({ easingType: 'easeOutQuart' });
								
			});
	</script>
	<!-- //here ends scrolling icon -->
	<script src="js/bars.js"></script>
	<script type="text/javascript">
	function InvalidMsg(textbox) {
	     if(textbox.length==0){
	        textbox.setCustomValidity('please enter your userid.');
	    }    
	    else {
	        textbox.setCustomValidity('');
	    }
	    return true;
	}
	
	</script>
	
	
</body>
<!-- //Body -->
</html>
<!-- //html -->