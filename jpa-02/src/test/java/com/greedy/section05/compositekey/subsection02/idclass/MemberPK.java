package com.greedy.section05.compositekey.subsection02.idclass;

import java.io.Serializable;
import java.util.Objects;

/*
   IdClass 타입으로 쓰인 클래스도 1차 캐시에서 식별자로 쓰이는 객체 이므로 (equals, hashCode) 메소드를 오버라이딩 해야한다.
 */
public class MemberPK implements Serializable {

    private int memberNo;
    private String memberId;

    public int getMemberNo() {
        return memberNo;
    }

    public String getMemberId() {
        return memberId;
    }

    public MemberPK(int memberNo, String memberId) {
        this.memberNo = memberNo;
        this.memberId = memberId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberPK memberPK = (MemberPK) o;
        return memberNo == memberPK.memberNo && Objects.equals(memberId, memberPK.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberNo, memberId);
    }
}
