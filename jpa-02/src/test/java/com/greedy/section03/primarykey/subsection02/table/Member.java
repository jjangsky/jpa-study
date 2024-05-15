package com.greedy.section03.primarykey.subsection02.table;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name= "MEMBER_SUB02")
@Table(name = "tbl_member_section02")
@TableGenerator(  // 번호를 생성하기 위한 테이블을 생성
        name = "member_seq_table_generator",
        table = "tbl_my_seq",
        pkColumnValue = "my_seq_member_no",
        allocationSize = 1
)
public class Member {
    @Id
    @Column(name = "MEMBER_NO")
    @GeneratedValue(strategy = GenerationType.TABLE, generator ="member_seq_table_generator")
    private int memberNo;
    @Column(name = "MEMBER_ID")
    private String memberId;
    @Column(name = "MEMBER_PWD")
    private String memberPwd;
    @Column(name = "MEMBER_NAME")
    private String nickName;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "ENROLL_DATE")
    private Date enrollDate;
    @Column(name = "MEMBER_ROLE")
    private String membberRole;
    @Column(name = "STATUS")
    private String status;

    public int getMemberNo() {
        return memberNo;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getMemberPwd() {
        return memberPwd;
    }

    public String getNickName() {
        return nickName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public String getMembberRole() {
        return membberRole;
    }

    public String getStatus() {
        return status;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setMemberPwd(String memberPwd) {
        this.memberPwd = memberPwd;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    public void setMembberRole(String membberRole) {
        this.membberRole = membberRole;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

