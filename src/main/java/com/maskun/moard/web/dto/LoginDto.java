package com.maskun.moard.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

//@JsonNaming 해당 클래스의 필드를 괄호안에 있는 기법으로 매핑하게끔 해주는 애노테이션으로 과거에는 PropertyNamingStrategy 를 기법으로 사용했으나 사장되었고 현재는 PropertyNamingStrategies를 사용한다. SnakeCaseStrategy.class는 필드를 카멜타입에서 스네이크타입으로 매핑하여 역직렬화 하겠다는 의미이며 Restcontroller에서 @requestBody의 인자로 지정한 DTO인 현 클래스에서 jackson 라이브러리로 매핑할 때 해당 기법을 적용시켜 매핑하게 된다. 따라서 Jackson 라이브러리를 사용할 때만 @JsonNaming 애노테이션이 작동한다. 나는 되도록 클라이언트의 http 요청에 있어서는 대중적인 스테이크타입 json key 네이밍규칙을 따르고자 했다. 자바 내부에서는 주로 카멜타입으로 필드를 명명하니 @JsonNaming 를 반영했다.
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Setter
@Getter
public class LoginDto {
    private String userId;
    private String userPassword;

}
