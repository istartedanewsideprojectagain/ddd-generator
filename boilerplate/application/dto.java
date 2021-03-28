package  {{ basePackage }}.application.{{ item }}.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class {{ name }}DTO {
    {% for field in fields %}
     private {{ field.type }} {{ field.name }};
    {% endfor %}
}
