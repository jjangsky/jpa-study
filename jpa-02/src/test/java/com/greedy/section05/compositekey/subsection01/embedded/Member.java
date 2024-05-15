package com.greedy.section05.compositekey.subsection01.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name="member_section05_subsection01")
@Table(name="tbl_member_section05_subsection01")
public class Member {
    // 복합키 예제
    @EmbeddedId
    private MemberPk memberPK;
    @Column(name="phone")
    private String phone;
    @Column(name="address")
    private String address;

    public Member(MemberPk memberPK, String phone, String address) {
        this.memberPK = memberPK;
        this.phone = phone;
        this.address = address;
    }

    public Member() {

    }

    public MemberPk getMemberPK() {
        return memberPK;
    }

    public void setMemberPK(MemberPk memberPK) {
        this.memberPK = memberPK;
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
