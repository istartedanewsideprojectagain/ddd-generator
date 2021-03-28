package {{ basePackage }}.infrastructure.{{ item }};

import {{ basePackage }}.domain.{{ item }}.{{ name }};
import {{ basePackage }}.domain.{{ item }}.{{ name }}Id;
import {{ basePackage }}.domain.{{ item }}.{{ name }}Repository;
import {{ basePackage }}.domain.exception.validation.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;
import java.util.UUID;

@Configuration
@AllArgsConstructor
public class {{ name }}RepositoryWrapper implements {{ name }}Repository {

    private final {{ name }}RepositoryJPA {{ item }}RepositoryJPA;
    private final {{ name }}ModelMapper {{ item }}ModelMapper;

    @Override
    public {{ name }} save({{ name }} entity) throws ValidationException {
        {{ name }}Model {{ item }}Model = {{ item }}ModelMapper.fromDomain(entity);

        {{ item }}Model = {{ item }}RepositoryJPA.save({{ item }}Model);

        return {{ item }}ModelMapper.toDomain({{ item }}Model);
    }

    @Override
    public void remove({{ name }}Id id) {
        {{ item }}RepositoryJPA.deleteById(id.get());
    }

    @Override
    public Optional<{{ name }}> findById({{ name }}Id id) throws ValidationException {
        Optional<{{ name }}Model> o{{ name }}Model = {{ item }}RepositoryJPA.findById(id.get());

        if(o{{ name }}Model.isEmpty()) return Optional.empty();

        return Optional.of({{ item }}ModelMapper.toDomain(o{{ name }}Model.get()));
    }

    @Bean
    protected {{ name }}Repository {{ item }}Repository( {{ name }}RepositoryJPA {{ item }}RepositoryJPA, {{ name }}ModelMapper {{ item }}ModelMapper){
        return new {{ name }}RepositoryWrapper({{ item }}RepositoryJPA,{{ item }}ModelMapper);
    }

}
