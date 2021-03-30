package  {{ basePackage }}.application.{{ typeName }};


import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import {{ basePackage }}.domain.{{ typeName }}.{{ type }};

{% for command in commands %}

import {{ basePackage }}.application.{{ typeName }}.command.{{ type }}{{ command.name }}Command;
import {{ basePackage }}.application.{{ typeName }}.command.{{ type }}{{ command.name }}CommandMapper;

{% endfor %}

{% for dto in dtos %}
import {{ basePackage }}.application.{{ typeName }}.dto.{{ type }}{{ dto.name }}DTO;
import {{ basePackage }}.application.{{ typeName }}.dto.{{ type }}{{ dto.name }}DTOMapper;
{% endfor %}

import {{ basePackage }}.domain.{{ typeName }}.{{ type }};
import {{ basePackage }}.domain.{{ typeName }}.{{ type }}Repository;


@Service
@AllArgsConstructor
public class {{ type }}Facade {
    private {{ type }}Repository {{ typeName }}Repository;

{% for command in commands %}
private {{ type }}{{ command.name }}CommandMapper {{ typeName }}{{ command.name }}CommandMapper;
{% endfor %}

{% for dto in dtos %}
private {{ type }}{{ dto.name }}DTOMapper {{ typeName }}{{ dto.name }}DTOMapper;
{% endfor %}

}
