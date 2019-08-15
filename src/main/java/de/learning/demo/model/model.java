package de.learning.demo.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;


@Data
@Builder
public class model {
    @Getter
    private String id;
    private String headline;
    private String description;
    private String article;
}
