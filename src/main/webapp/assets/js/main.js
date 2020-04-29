
	function filtrar() {		
		var filtro = document.querySelector('#pesquisa');

		filtro.addEventListener('input', function(){

			var trs = document.querySelectorAll('.trs');
			if(this.value.length > 0)
			{
				trs.forEach(function(tr) {

				var id = tr.querySelector('.id');
				var referencia = tr.querySelector('.referencia');
				var subReferencia = tr.querySelector('.subReferencia'); 
				var erro = tr.querySelector('.erro'); 
				var usuario = tr.querySelector('.usuario');
				var solucao = tr.querySelector('.solucao');
				var dataAlteracao = tr.querySelector('.dataAlteracao');
				var codigo = tr.querySelector('.codigo');

				if(null != id) { id = id.textContent; }
				if(null != referencia) { referencia = referencia.textContent; }
				if(null != subReferencia) { subReferencia = subReferencia.textContent; }
				if(null != erro) { erro = erro.textContent; }
				if(null != usuario) { usuario = usuario.textContent; }
				if(null != solucao) { solucao = solucao.textContent; }
				if(null != dataAlteracao) { dataAlteracao = dataAlteracao.textContent; }
				if(null != codigo) { codigo = codigo.textContent; }

				var expressao = new RegExp(filtro.value,'i');
					
					if(!expressao.test(id) && !expressao.test(referencia) && !expressao.test(subReferencia) && !expressao.test(erro)
							&& !expressao.test(usuario) && !expressao.test(solucao) && !expressao.test(dataAlteracao) && !expressao.test(dataAlteracao))
					{
						tr.classList.add('remove-table');	
					}
					else
					{
						tr.classList.remove('remove-table');
					}
				});
			}
			else
			{
				for(var i = 0; i < trs.length; i++) 
				{
					var tr = trs[i];
					tr.classList.remove('remove-table');			
				}
			}
		});
	}

//get combos
	$('select#codigoReferencia').on('change', function() 
	{
		combosConsultarSubReferencias();
	});

	
	function combosConsultarSubReferencias() {

		$('select#codigoSubReferencia').empty();
		var codigo = $('select#codigoReferencia').val();

		dados = { codigo: codigo };
		
		$.ajax({
            url : "/wiki-tools/sub-referencia/ajaxComboSubReferencia",
            type : "POST",
            data : dados,
            dataType : "json"
    	}).done(function(data) {
    		
    		$('select#codigoSubReferencia').append('<option value="0">Selecione...</option>');

			$.each(data.subReferencias, function(key, value)
			{
				$('select#codigoSubReferencia').append('<option value="'+ value.codigoSubReferencia +'">'+ value.descricaoSubReferencia +'</option>');
			});

    	}).fail(function() {
    		console.log("Falhou");
    	}).always(function() {
    		console.log("Finish");
    	});
	}
	
	function getProximo() {

		var codigo = $('input#codigoUsuario').val();

		dados = { codigo: codigo };
		
		$.ajax({
            url : "/wiki-tools/usuario/ajaxProximo",
            type : "POST",
            data : dados,
            dataType : "json"
    	}).done(function(data) {
    		
			$('input#codigoUsuario').val(data.proximo);
			$('input#codigoUsuario').addClass('json-sucess');
			
			setTimeout(function(){
				$('input#codigoUsuario').removeClass('json-sucess');
			}, 3000);				

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
	
	function populateAlert() {

		var message = $('.message');
		if(message.text() != '') 
		{
			$('#div-alert').addClass('alert alert-danger');
		}

		if(message.text() != '')
		{
			$('#div-info').addClass('alert alert-info');
		}

		var errors = $('.errors');
		if(errors.text() != '')
		{
			errors.addClass('alertErrors');
		}
	}

	
	$('#pesquisa').click(function(){
		filtrar();
	});
	
	function main() {
		console.log("import, teste subida 2");
	}
	
	$(document).ready(function(){
		
		populateAlert();
	});
