package {{ basePackage }}.infrastructure.{{ item }};

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "{{ item }}")
@Table(name = "{{ item }}")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class {{ name }}Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    {% for field in fields %}
private {{ field.type }} {{ field.name }};
    {% endfor %}
}