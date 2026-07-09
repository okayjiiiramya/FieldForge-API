package io.labforward.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class CategoryJson {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Attributes are required")
    private List<AttributeJson> attributes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AttributeJson> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeJson> attributes) {
        this.attributes = attributes;
    }
}
