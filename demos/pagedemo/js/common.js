$(function(){ 
		//取到窗口的高度 
		var winH = $(window).height(); 
		//取到页面的高度 
		var bodyH = $(document).height(); 
		if(bodyH > winH){ 
			window.parent.document.getElementById("testFrame").height=bodyH; 
		}else{ 
			window.parent.document.getElementById("testFrame").height=winH; 
		} 
	}); 