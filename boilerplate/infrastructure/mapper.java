package {{ basePackage }}.infrastructure.{{ item }};

import {{ basePackage }}.domain.EntityMapper;
import {{ basePackage }}.domain.{{ item }}.{{ name }};
import {{ basePackage }}.domain.{{ item }}.{{ name }}Id;
import {{ basePackage }}.domain.exception.validation.ValidationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class {{ name }}ModelMapper implements EntityMapper<{{ name }},{{ name }}Model> {
    @Override
    public {{ name }} toDomain({{ name }}Model model) throws ValidationException {
    }

    @Override
    public {{ name }}Model fromDomain({{ name }} entity) {
        Long id = entity.getId() != null ? entity.getId().get() : null;
    }
}