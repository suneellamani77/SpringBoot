
$(document).ready(function(){
	
	switch (menu) {
	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'Service':
		$('#service').addClass('active');
		break;
	case 'Home':
		$('#home').addClass('active');
		break;
	default:
		$('#home').addClass('active');
		break;
	}
	
	
	$('#fun1').change(function(){
		
		var url=$(this).val();
		if(url==''){
			$('#tbd').empty();
		}
		$.ajax({
			method: "GET",
			url : "/addPermission?url="+url,
			
			success:function(data){
				var tbody = $("#tbd", data);
				$("#tbd").html(tbody.html());
			},
			error: function(){
				alert('data not loaded')
			}
			
		});
		
	});
	
});
