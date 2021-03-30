package {{ basePackage }}.application.{{ typeName }}.dto;

import {{ basePackage }}.domain.{{ typeName }}.{{ type }};
import org.springframework.stereotype.Service;

@Service
public class {{ dtoMapperType }} {
    public {{ dtoType }} fromDomain({{ type }} {{ typeName }}){
        return new {{ dtoType }}({{ item }});
    }
}
