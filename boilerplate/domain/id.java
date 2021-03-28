package {{ basePackage }}.domain.{{ item }};

import {{ basePackage }}.domain.Id;
import {{ basePackage }}.domain.exception.validation.ValidationException;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class {{ name }}Id extends Id<Long> {
    public {{ name }}Id(Long id) throws ValidationException {
        super(id);
    }
}