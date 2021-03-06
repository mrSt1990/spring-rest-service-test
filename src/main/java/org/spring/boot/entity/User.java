package org.spring.boot.entity;

import org.spring.boot.validator.RoleExists;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

/**
 * Created by Aleksey Stoyokha on 26.01.17.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * User login. Must not be empty.
     */
    @Column(name = "login")
    @NotNull(message = "login must not be empty")
    private String login;

    /**
     * User name. Must not be empty.
     */
    @Column(name = "name")
    @NotNull(message = "name must not be empty")
    private String name;

    /**
     * User password. Must not be empty and contain at least one numeric and cursive character.
     */
    @Column(name = "password")
    @NotNull(message = "password must not be empty")
    @Pattern(regexp = ".*([A-ZА-Я].*[0-9]|[0-9].*[A-ZА-Я]).*",
            message = "password must contain at least one numeric and cursive character")
    private String password;

    /**
     * Set of assigned roles. Role must exist.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @RoleExists
    private Set<Role> roles;

    public User() {
    }

    public User(String login, String name, String password) {
        this.login = login;
        this.name = name;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
