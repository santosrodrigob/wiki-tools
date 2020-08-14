<script type="text/javascript">

	console.log("estado 3");
	
	function gravar() {

		var form = $('form#formClienteNovo');
		
		var dados = form.serialize();
		console.log(dados);
		
 		$.ajax({
            url : "/wiki-tools/cliente-estrutura/gravar",
            type : "POST",
            data : dados,
            dataType : "json"
    	}).done(function(data) {
    		
    		console.log(data.status);
    		
    		console.log("requisitou")
    		
/* 			$('input#codigoUsuario').val(data.proximo);
			$('input#codigoUsuario').addClass('json-sucess');
			
			setTimeout(function(){
				$('input#codigoUsuario').removeClass('json-sucess');
			}, 3000);
*/
    	}).fail(function() {
    		console.log("Falhou");
			$('input#codigoUsuario').addClass('json-error');
			setTimeout(function(){
				$('input#codigoUsuario').removeClass('json-error');
			}, 3000);				

    	}).always(function() {
    		console.log("Finish");
    	});
	}
	
	$(document).ready(function(){
		
	});

</script>