package com.maskun.moard.domain.account;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Entity
public class Account {
    @Id
    private String accountId;
    @Column(nullable = false) // nullable 과 @NotNull에 대한 비교내용 https://kafcamus.tistory.com/15
    private String accountPw;


}
