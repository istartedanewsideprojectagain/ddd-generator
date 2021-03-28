package {{ basePackage }}.infrastructure.{{ item }};

import org.springframework.data.repository.CrudRepository;


public interface {{ name }}RepositoryJPA extends CrudRepository<{{ name }}Model, Long> {
}
