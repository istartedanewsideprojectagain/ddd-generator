package {{ basePackage }}.application.{{ typeName }}.command;

import {{ basePackage }}.domain.{{ typeName }}.{{ type }};
import {{ basePackage }}.domain.exception.validation.ValidationContext;
import {{ basePackage }}.domain.exception.validation.ValidationException;
import {{ basePackage }}.domain.exception.validation.validator.Validator;

import org.springframework.stereotype.Service;


@Service
public class {{ commandMapperType }} {
    public {{ type }} toDomain({{ commandType }} command) {

    }
}
