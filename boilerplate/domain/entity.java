package {{ basePackage }}.domain.{{ item }};

import {{ basePackage }}.domain.KeyedEntity;
import {{ basePackage }}.domain.exception.validation.ValidationContext;
import {{ basePackage }}.domain.exception.validation.ValidationException;
import {{ basePackage }}.domain.exception.validation.validator.NotNullValidator;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class {{ name }} extends KeyedEntity<{{ name }}Id> {


    {% for field in fields %}
     private {{ field.type }} {{ field.name }};
    {% endfor %}

    //TODO: Constructors

    //TODO: ValidateOrThrow
}