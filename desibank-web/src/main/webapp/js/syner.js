     
     function callme(){
	 
     }
     
     function validateForm(){
    	 var pname=document.forms[0].nameId.value;
    	 if(pname.length==0){
    		 alert("Name cannot be blank!");
    		 document.forms[0].nameId.focus();
    		 return;
    	 }else if(pname.length <= 3){
    		 alert("length should be greater than 3");
    		 return;
    	 }
       //submiting form through java script code.
        document.forms[0].action=pname+".jsp";
        document.forms[0].submit();
     }