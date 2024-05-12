package com.greedy.section01.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;
@Entity(name= "MEMBER")
@Table(name = "tbl_member_section01")
public class Member {
    @Id
    @Column(name = "MEMBER_NO")
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


}
