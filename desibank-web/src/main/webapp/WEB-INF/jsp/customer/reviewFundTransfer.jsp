<%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form"%>
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
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.css" />
	
<!-- //.css files -->
<!-- Default-JavaScript-File -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.js"></script>
<!-- //Default-JavaScript-File -->
<!-- fonts -->
	<link href="//fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i&amp;subset=cyrillic,cyrillic-ext,greek,greek-ext,latin-ext,vietnamese" rel="stylesheet">
	<link href="//fonts.googleapis.com/css?family=Ropa+Sans:400,400i&amp;subset=latin-ext" rel="stylesheet">
<!-- //fonts -->
<!-- scrolling script -->
<script type="text/javascript">
	jQuery(document).ready(function($) {
		
		 $(".form_datetime").datetimepicker({
		        format: "dd MM yyyy - hh:ii"
		    });
		
		$("#schedulePaymentDate").hide();
		
		$("input[type='radio'][name='paymentOption']").click(function(){
			var selectvalue=$("input[name='paymentOption']:checked").val()
			if(selectvalue=='PayNow'){
				$("#schedulePaymentDate").hide();
			}else{
				$("#schedulePaymentDate").show();
			}
		});
		
		$("#fromAccount").change(function(){
				var data =$('#fromAccount option:selected').text()
				 var tokens=data.split("-");
				$("#accountType").val(tokens[1].trim());
		});
		
		
		$(".scroll").click(function(event){		
			event.preventDefault();
			$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
		});
		
		
		var contextPath="${pageContext.request.contextPath}";
		
			$("a[id$='deletePayeeRecord']").click(function() {
						//this will represent the element where we have performed clicked event
						var sno=$(this).attr("payeeRecord");
						//$("#"+sno).hide();
						//$.getJSON("v1/ajaxCall",{ userid: sno,password:"hello"}, function(data)
								//$.getJSON("${pageContext.request.contextPath}/v1/deletePayeeByAjax", { sno: sno}, function(jsonResponse)
							$.getJSON("${pageContext.request.contextPath}/v1/deletePayeeByAjax", { sno:sno}, function(jsonResponse){
								if(jsonResponse.status=="success"){
									//$("#pmessage").html(data.description);
									$("#"+sno).hide();
								 }else{
									//$("#pmessage").html("Sorry data could not be deleted");
								}									
				   }); 
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

<section class="blog" id="blog">
	<div class="container">
		<div class="blog-heading">
			<img alt="" src="${pageContext.request.contextPath}/images/fund-transfer.png"/>
			<h3  style="text-align: left;display: inline;" >Payment Review Page</h3>
			<br/>
			<br/>
		
			<sp:form id="fundTransferForm" method="post" 
			commandName="transferMoneyForm" action="${pageContext.request.contextPath}/customer/transactionMoney.htm">
				<table>
				
			<tr>
            <td style="text-align: center;">From Account </td>
            <td>     
             <sp:input path="fromAccount" style="width: 200px;background-color: #d9edf7;color:black;" readonly="true"/>
            </td>
		    </tr>
		    <tr>
            <td>&nbsp; </td>
            <td>&nbsp; 
            <sp:hidden path="paymentOption"/>
            </td>
		    </tr>
		        <tr>
            <td style="text-align: center;">Type of account    : </td>
            <td><sp:input  path="accountType" class="form-control"   style="width: 200px;background-color: #d9edf7;color:black;" readonly="true"/></td>
		    </tr>
		      <tr>
            <td>&nbsp; </td>
            <td>&nbsp; </td>
		    </tr>
		    
		     
		    
		       <tr>
            <td>&nbsp; </td>
            <td>&nbsp; </td>
		    </tr>
				
			<tr>
            <td>Select a Payee to Make a Payment:&nbsp;&nbsp;&nbsp;</td>
		    <td>
		    <sp:input  path="selectedPayeeName" class="form-control"   style="width: 200px;background-color: rgba(255, 185, 0, 0.32);;color:black;" readonly="true"/>
		    </td>
            </tr>
            
               <tr>
            <td>&nbsp; </td>
            <td>&nbsp; </td>
		    </tr>
		       <tr>
            <td>&nbsp; </td>
            <td>&nbsp; </td>
		    </tr>
        
        
			<tr>
			<td style="text-align: center;"> Enter Amount:</td> 
  		    <td><sp:input  path="amount"  class="form-control"   style="width: 200px;background-color: #d9edf7;height: 28px;;display:inline;color:black;" readonly="true"/> Rs.
  		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		     Description
  		    <sp:input  path="description" class="form-control"  style="width: 340px;background-color: #d9edf7;display:inline;margin-left:10px;color:black;" readonly="true"/> 
  		    </td>
  		    </tr>
  		      <tr>
            <td>&nbsp; </td>
            <td>&nbsp; </td>
		    </tr>
		      <tr>
            <td>&nbsp; </td>
            <td>&nbsp; </td>
		    </tr>
			  <tr>
            <td>&nbsp; </td>
            <td>&nbsp; </td>
		    </tr>
			 
			 <tr id="schedulePaymentDate">
            <td style="text-align: center;">Schedule Payment Date </td>
            <td>
            <div class="input-append date form_datetime">
    			<input size="16" type="text" value="" readonly  name="paymentDateTime" style="width: 200px;background-color: #d9edf7;display:inline;margin-left:10px;color:black;">
    				<span class="add-on"><i class="icon-th"></i></span>
			</div>
            </td>
		    </tr>
		      <tr>
            <td>&nbsp; </td>
            <td>&nbsp; </td>
		    </tr>
			<tr>
			<td colspan="2" style="text-align: right;">
			<input type="submit" id="transfer" name="transfer" value="Pay Now" style=" color: #FFFFFF; background: #ffb900;width: 180px;height: 30px;color:black;" /></td>
			</tr>
			</table>
			</sp:form>
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
	

<!-- footer -->
<jsp:include page="../common/footer.jsp"/>
<!-- //footer -->

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
