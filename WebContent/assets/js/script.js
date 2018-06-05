$(document).ready(function(){
	
	setInterval("getSaldo()",3000);
	
});

function getSaldo(){
	
	$.ajax({
		url:"./saldo",
		type:"POST",
		data:{
			verificarSaldo:true
		},
		success:function(result){
			
			console.log(result);
			
			$("#saldo").html(result);
			
		},
		error:function(p1,p2,p3){
			
			console.log(p1);
			console.log(p2);
			console.log(p3);
			
		}
	});
	
}
