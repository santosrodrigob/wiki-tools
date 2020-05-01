/**
 * @author RodrigoBorges.
 * @date 01/04/2020
 */
package br.com.tools.validate;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.tools.model.LicaoAprendidaDTO;

public class LicaoAprendidaValidate implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return LicaoAprendidaDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmpty(errors, "erro", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "solucao", "field.required");
		
		LicaoAprendidaDTO licaoAprendidaDTO = (LicaoAprendidaDTO) target;
		if(licaoAprendidaDTO.getCodigoReferencia() <= 0) {
			errors.rejectValue("codigoReferencia", "field.required");
		}
		if(licaoAprendidaDTO.getCodigoSubReferencia() <= 0) {
			errors.rejectValue("codigoSubReferencia", "field.required");
		}
		if(licaoAprendidaDTO.getCodigoUsuario() <= 0) {
			errors.rejectValue("codigoUsuario", "field.required");
		}
	}
}
