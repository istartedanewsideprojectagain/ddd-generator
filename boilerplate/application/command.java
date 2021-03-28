package  {{ basePackage }}.application.{{ item }}.command;

import lombok.Data;

@Data
public class {{ name }}Command {
    {% for field in fields %}
     private {{ field.type }} {{ field.name }};
    {% endfor %}
}