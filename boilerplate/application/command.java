package  {{ basePackage }}.application.{{ typeName }}.command;

import lombok.Data;

@Data
public class {{ commandType }} {
    {% for field in fields %}
     private {{ field.type }} {{ field.name }};
    {% endfor %}
}
