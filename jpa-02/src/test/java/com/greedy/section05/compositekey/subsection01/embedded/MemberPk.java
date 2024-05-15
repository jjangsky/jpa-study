package com.greedy.section05.compositekey.subsection01.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

/*
 복합키로 사용될 Class는 `@Embeddable` 어노테이션을 붙여주고 직렬화 필요함
 */

/*
 * Embeddable 타입은 하나의 값의 묶음이자 불변 객체로 다루는 타입이다.
 * (불변 객체 : setter가 없고 필드값에 변화를 주고 싶으면 새로운 필드를 가지는
 * 새로운 객체가 생성 되어야 한다. -> 필드가 여러개 이지만 하나의 값으로 활동하기 위해)
 * 그리고 equals, hashCode를 반드시 오버라이딩 해야한다.
 * Vo와 유사하며 @EmbeddedId로 복합키를 표현하고자 할 때 쓰이기도 한다.
 */
@Embeddable
public class MemberPk implements Serializable {

    @Column(name = "MEMBER_NO")
    private int memberNo;
    @Column(name = "MEMBER_ID")
    private String memberId;

    public MemberPk() {}

    public MemberPk(int memberNo, String memberId) {
        this.memberNo = memberNo;
        this.memberId = memberId;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public String getMemberId() {
        return memberId;
    }

    // 오버라이딩 필요함
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
