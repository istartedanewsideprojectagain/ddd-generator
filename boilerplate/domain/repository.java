package {{ basePackage }}.domain.{{ item }};

import {{ basePackage }}.domain.Repository;
import {{ basePackage }}.domain.exception.validation.ValidationException;

import java.util.Optional;
import java.util.UUID;

public interface {{ name }}Repository extends Repository<{{ name }}, {{ name }}Id> {

}