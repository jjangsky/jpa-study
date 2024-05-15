package com.greedy.section05.compositekey.subsection02.idclass;

import jakarta.persistence.*;

@Entity(name="member_section05_subsection02")
@Table(name="tbl_member_section05_subsection02")
@IdClass(MemberPK.class)
public class Member {

    // 좀 더 직관적으로 복합키를 표현할 수 있다.
    // 상단에 `@IdClass` 어노테이션을 붙여주고 복합키로 사용할 필드를 선언한다.

    @Id
    @Column(name="member_no")
    private int memberNo;

    @Id
    @Column(name="member_id")
    private String memberId;

    @Column(name="phone")
    private String phone;
    @Column(name="address")
    private String address;

    public Member() {
    }

    public Member(int memberNo, String memberId, String phone, String address) {
        this.memberNo = memberNo;
        this.memberId = memberId;
        this.phone = phone;
        this.address = address;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
