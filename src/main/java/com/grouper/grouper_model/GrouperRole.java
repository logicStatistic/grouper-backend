package com.grouper.grouper_model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "role")
public class GrouperRole {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )

    @Column(name = "role_id")
    private Integer roleId;
    private String authority;

    public GrouperRole() {
    }

    public GrouperRole(Integer roleId,
                       String authority) {
        this.roleId = roleId;
        this.authority = authority;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "GrouperModel{" +
                "roleId=" + roleId +
                ", authority='" + authority + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrouperRole that = (GrouperRole) o;
        return Objects.equals(roleId, that.roleId)
                && Objects.equals(authority, that.authority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, authority);
    }
}

