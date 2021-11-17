package apap.tutorial.cineplux.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UmurPenjaga {
    @JsonProperty("nama")
    private String name;

    @JsonProperty("age")
    private Integer age;

}
