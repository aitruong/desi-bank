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
<!-- //Default-JavaScript-File -->
<!-- fonts -->
	<link href="//fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i&amp;subset=cyrillic,cyrillic-ext,greek,greek-ext,latin-ext,vietnamese" rel="stylesheet">
	<link href="//fonts.googleapis.com/css?family=Ropa+Sans:400,400i&amp;subset=latin-ext" rel="stylesheet">
<!-- //fonts -->
<!-- scrolling script -->
<script type="text/javascript">

function openAccountGenerationPopup(name,email,csaid){
		$("#acustomerName").val(name);
		$("#aemail").val(email);
		$("#userid").val(csaid);
		$("#approveReuqestModel").modal("show");
}

	function openRejectPopup(name,email,csaid){
		$("#customerName").val(name);
		$("#email").val(email);
		$("#pcsaid").val(csaid);
		$("#rejectReuqestModel").modal("show");
	}
	
	jQuery(document).ready(function($) {
		$("#reason").keyup(function(){
			$("#reasonErrorMessage").html("");
		});
		
		$("#rejectApplication").click(function(){
				var reason=$("#reason").val();
				if(reason.trim().length==0){
					$("#reasonErrorMessage").html("Reason cannot be empty.............");
					$("#reason").focus();
					return;
				}else{
					var contextPath="${pageContext.request.contextPath}";
					$.ajax({url:contextPath+"/employee/rejectSavingRequest.htm", type: 'POST',data:$("#rejectSavingRequestForm").serialize(),success:function(jsonData) {  //data= this.responseText
						//data is JavaScript object against JSON response coming fromm the server
							console.log(jsonData);
							if(jsonData=='success') {
								var  pcsaid =$("#pcsaid").val();
								$("#"+pcsaid).hide();
								$("#rejectReuqestModel").modal("hide");
							}
					}
					});	 //end of the ajax			
				}
		}); //end of click
		
		
		$("#approveApplication").click(function(){
				var contextPath="${pageContext.request.contextPath}";
				 //Creating JavaScript object using html form data 
				 var jdata=$("#approveSavingRequestForm").serializeArray();
				 //to convert form data into JavaScript
			   	 var jsonData = {};
				 jdata.forEach((item) => {
						jsonData[item.name]=item.value;
			   	 }); 
				 
				//converting JavaScript Object into- the  JSON.stringify(jsonData) 
				$.ajax({url:contextPath+"/v1/employee/create-customer-account", contentType: "application/json", type: 'POST',data:JSON.stringify(jsonData),success:function(jsonData) {  //data= this.responseText
					//data is JavaScript object against JSON response coming fromm the server
						console.log(jsonData);
						if(jsonData.status=='success') {
							//userid 
							var  pcsaid =$("#userid").val();
							$("#"+pcsaid).hide();
							$("#approveReuqestModel").modal("hide");
							$("#AppMessage").html(jsonData.message);
							
						}
				}
				});	 //end of the ajax			
	}); //end of click
		
		
		$("#rejectClose").click(function(){
		  	$("#rejectReuqestModel").modal("hide");
		});
		$("#approveClose").click(function(){
		  	$("#approveReuqestModel").modal("hide");
		});
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
	
<jsp:include page="employee-menu.jsp"/>
<!--team-->
<jsp:include page="dashboard-options.jsp"/>
<!--//team-->
<hr/>
<div class="team" id="team" style="margin-top: -118px;">
	<div class="container">
	
	  <div style="float: right;">
     	<br/>
     </div>
 <span id="AppMessage"></span>    
 <table class="table table-bordered">
        <thead>
            <tr>
                <th>Row</th>
                <th>Name</th>
                <th>Email</th>
                <th>Mobile</th>
                 <th>Location</th>
                   <th>Job Title</th>
                <th>Photo</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach items="${customerFormList}" var="item"  varStatus="p">
            <tr id="${item.userid}">
                <td>${p.count}</td>
                <td>${item.name }</td>
               <td>${item.email }</td>
                 <td>${item.mobile }</td>
                   <td>${item.address }</td>
                   <td>${item.jobTitle }</td>
                    <td><img src="${pageContext.request.contextPath}/findImageByUserid.htm?userid=${item.userid}" style="height: 42px;"></td>
                    <td>
                      <a href="javascript:openRejectPopup('${item.name }','${item.email }','${item.userid}');">
                    <img src="${pageContext.request.contextPath}/images/icon/reject.png">
                      </a>
                    &nbsp;&nbsp;&nbsp;
                       <a href="javascript:openAccountGenerationPopup('${item.name }','${item.email }','${item.userid}');">
                    <img src="${pageContext.request.contextPath}/images/icon/approve.png">
                    </a>
                    </td>
            </tr>
            </c:forEach>
              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
               <td>&nbsp;</td>
                 <td>&nbsp;</td>
                   <td>&nbsp;</td>
                   <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
            </tr>
            
                <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
               <td>&nbsp;</td>
                 <td>&nbsp;</td>
                   <td>&nbsp;</td>
                   <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
            </tr>
            
                <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
               <td>&nbsp;</td>
                 <td>&nbsp;</td>
                   <td>&nbsp;</td>
                   <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
            </tr>
                <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
               <td>&nbsp;</td>
                 <td>&nbsp;</td>
                   <td>&nbsp;</td>
                   <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
            </tr>
                <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
               <td>&nbsp;</td>
                 <td>&nbsp;</td>
                   <td>&nbsp;</td>
                   <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
            </tr>
                <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
               <td>&nbsp;</td>
                 <td>&nbsp;</td>
                   <td>&nbsp;</td>
                   <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
            </tr>
                <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
               <td>&nbsp;</td>
                 <td>&nbsp;</td>
                   <td>&nbsp;</td>
                   <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
            </tr>
        </tbody>
    </table>
    </div>
    </div>

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
						<img src="images/business.jpg" alt="" />
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

<!-- 	<script src="{pageContext.request.contextPath}/js/jarallax.js"></script>
	<script src="{pageContext.request.contextPath}/js/SmoothScroll.min.js"></script>
	<script type="text/javascript">
		/* init Jarallax */
		$('.jarallax').jarallax({
			speed: 0.5,
			imgWidth: 1366,
			imgHeight: 768
		})
	</script> -->

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
	
	  <!-- Modal -->
  <div class="modal fade" id="loginModel" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
     	<div>
			<div class="container">
				<div>
					<div class="col-md-5">
						<form id="loginForm" action="validate.htm" method="post">
							 <img src="${pageContext.request.contextPath}/images/login.png" style="display: inline;"/><h5 class="modal-title" style="display: inline;font-size: 16px;">Login</h5>
							<table>
							
									<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>Username</td>
									<td><input type="text" name="userid" class="form-control" required="" style="background-color: #d9edf7;margin-left: 20px;width: 350px;color:black;"  oninvalid="InvalidMsg(this);"></td>
								</tr>
								
									<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>Password</td>
									<td><input type="password" required="" name="password" class="form-control" style="background-color: #d9edf7;margin-left: 20px;width: 350px;color:black;" ></td>
								</tr>
									<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
									<tr>
									<td>&nbsp;</td>
									<td>&nbsp;
									<input type="submit" name="login" style=" color: #FFFFFF;
    background: #ffb900;
    border: 2px solid #ffb900;
    text-transform: uppercase;
    padding: .2em 1em;
    font-size: 1.3em;
    font-family: 'Ropa Sans', sans-serif;" value="Login"></td>
								</tr>
									<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
									<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
							</table>							
						</form>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
    </div>
  </div>
  </div>
  
  	  <!-- Modal -->
  <div class="modal fade" id="rejectReuqestModel" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
     	<div>
			<div class="container">
				<div>
					<div class="col-md-5">
						<form id="rejectSavingRequestForm" action="{pageContext.request.contextPath}/employee/rejectSavingRequest.htm" method="post">
								<input type="hidden"  value=""  name="csaid"   id="pcsaid"/>
							 <img src="{pageContext.request.contextPath}/images/login.png" style="display: inline;"/><h5 class="modal-title" style="display: inline;font-size: 16px;">Reject Request</h5>
							 <br/>
							 <br/>
							<span id="errorMessage" style="color: red;font-size: 15px;"></span> 
							<table>
							
									<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>Username</td>
									<td><input type="text"  id="customerName" name="name" class="form-control"  style="background-color: #d9edf7;margin-left: 20px;width: 350px;color:black;" readonly="readonly"></td>
								</tr>
								
									<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr>
									<td>Email</td>
									<td><input type="text"  id="email"   name="email" class="form-control" style="background-color: #d9edf7;margin-left: 20px;width: 350px;color:black;"  readonly="readonly"></td>
								</tr>
								
									<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
									<tr>
									<td colspan="2" id="reasonErrorMessage" color: red;font-size: 15px;>&nbsp;</td>
								</tr>
								
								<tr>
									<td>Reason</td>
									<td>
										<textarea rows="3" cols="40" name="reason" id="reason" style=" border:1px solid;color:black;border-color: #d9edf7; "></textarea>
									</td>
								</tr>
									<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
									<tr>
									<td>&nbsp;</td>
									<td>&nbsp;
									<input type="reject" name="reject" style=" color: #FFFFFF;
    background: #ffb900;
    border: 2px solid #ffb900;
    text-transform: uppercase;
    padding: .2em 1em;
    font-size: 1.3em;
    font-family: 'Ropa Sans', sans-serif;" value="Reject Application"  id="rejectApplication">
    
    	<input type="button" id="rejectClose" style=" color: #FFFFFF;
    background: #ffb900;
    border: 2px solid #ffb900;
    text-transform: uppercase;
    padding: .2em 1em;
    font-size: 1.3em;
    font-family: 'Ropa Sans', sans-serif;" value="Close">
    </td>
								</tr>
									<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
									<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
							</table>							
						</form>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
    </div>
  </div>
  </div>
  
    	  <!-- Modal -->
  <div class="modal fade" id="approveReuqestModel" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
     	<div>
			<div class="container">
				<div>
					<div class="col-md-5">
						<form id="approveSavingRequestForm">
							<input type="hidden"  value=""  name="userid"   id="userid"/>
							 <img src="${pageContext.request.contextPath}/images/login.png" style="display: inline;"/><h5 class="modal-title" style="display: inline;font-size: 16px;">Create Account for Customer</h5>
							 <br/>
							 <br/>
							<span id="errorMessage" style="color: red;font-size: 15px;"></span> 
							<table>
							
									<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>Username</td>
									<td><input type="text"  id="acustomerName" name="name" class="form-control"  style="background-color: #d9edf7;margin-left: 20px;width: 350px;color:black;" readonly="readonly"></td>
								</tr>
								
									<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr>
									<td>Email</td>
									<td><input type="text"  id="aemail"   name="email" class="form-control" style="background-color: #d9edf7;margin-left: 20px;width: 350px;color:black;"  readonly="readonly"></td>
								</tr>
								
									<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
									<tr>
									<td colspan="2" id="areasonErrorMessage" color: red;font-size: 15px;>&nbsp;</td>
								</tr>
								
									<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
									<tr>
									<td>&nbsp;</td>
									<td>&nbsp;
									<input type="button"  style=" color: #FFFFFF;
    background: #ffb900;
    border: 2px solid #ffb900;
    text-transform: uppercase;
    padding: .2em 1em;
    font-size: 1.3em;
    font-family: 'Ropa Sans', sans-serif;" value="Approve Application"  id="approveApplication">
    
    	<input type="button" id="approveClose" style=" color: #FFFFFF;
    background: #ffb900;
    border: 2px solid #ffb900;
    text-transform: uppercase;
    padding: .2em 1em;
    font-size: 1.3em;
    font-family: 'Ropa Sans', sans-serif;" value="Close">
    </td>
								</tr>
									<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
									<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
							</table>							
						</form>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
    </div>
  </div>
  </div>
</body>
<!-- //Body -->
</html>
<!-- //html -->