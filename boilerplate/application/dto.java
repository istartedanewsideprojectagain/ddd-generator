package  {{ basePackage }}.application.{{ typeName }}.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class  {{ dtoType }} {
    {% for field in fields %}
     private {{ field.type }} {{ field.name }};
    {% endfor %}
}
