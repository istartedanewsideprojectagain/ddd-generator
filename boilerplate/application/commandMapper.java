package ch.chuv.pregrec.application.{{ item }}.command;

import ch.chuv.pregrec.domain.{{ item }}.{{ name }};
import ch.chuv.pregrec.domain.exception.validation.ValidationContext;
import ch.chuv.pregrec.domain.exception.validation.ValidationException;
import ch.chuv.pregrec.domain.exception.validation.validator.Validator;

import org.springframework.stereotype.Service;


@Service
public class {{ name }}{{ commandName }}Mapper {
    public {{ name }} toDomain({{ name }}{{ commandName }} command) {
        
    }
}