$(document).ready(function($) {
	
	$("#logoutLink").click(function(event){
		$("#logoutForm").submit();
	});
	
	$(".beer-row").click(function(event){
		window.location = $(this).data('href');
	});
	
	
	
	
});