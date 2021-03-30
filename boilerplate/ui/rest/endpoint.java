package {{ basePackage }}.ui.rest.{{ typeName }};

import {{ basePackage }}.application.{{ typeName }}.{{ type }}Facade;

    {% for endpoint in endpoints %}
import {{ basePackage }}.application.{{ typeName }}.command.{{ type }}{{ endpoint.name }}Command;
    {% endfor %}

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/{{ typeNamePlural }}")
@AllArgsConstructor
public class {{ type }}Endpoint implements I{{ type }}Endpoint {

    private final {{ type }}Facade {{ typeName }}Facade;



{% for endpoint in endpoints %}
    public ResponseEntity<?> {{ endpoint.name }}{{ type }}(@RequestBody {{ type }}{{ endpoint.name }}Command command) {
        //TODO:
    }
{% endfor %}
}

