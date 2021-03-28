package {{ basePackage }}.application.{{ item }};

import {{ basePackage }}.domain.{{ item }}.{{ name }};
import org.springframework.stereotype.Service;

@Service
public class {{ name }}{{ dtoName }}Mapper {
    public {{ name }}{{ dtoName }} fromDomain({{ name }} {{ item }}){
        return new {{ name }}{{ dtoName }}({{ item }});
    }
}