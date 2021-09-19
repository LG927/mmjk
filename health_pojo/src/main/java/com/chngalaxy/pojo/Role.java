package com.chngalaxy.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 角色
 */
public class Role implements Serializable {
    private Integer id;
    private String name; // 角色名称
    private String keyword; // 角色关键字，用于权限控制
    private String description; // 描述
    private Set<com.chngalaxy.pojo.User> users = new HashSet<com.chngalaxy.pojo.User>(0);
    private Set<com.chngalaxy.pojo.Permission> permissions = new HashSet<com.chngalaxy.pojo.Permission>(0);
    private LinkedHashSet<Menu> menus = new LinkedHashSet<Menu>(0);

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<com.chngalaxy.pojo.User> getUsers() {
        return users;
    }

    public void setUsers(Set<com.chngalaxy.pojo.User> users) {
        this.users = users;
    }

    public Set<com.chngalaxy.pojo.Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<com.chngalaxy.pojo.Permission> permissions) {
        this.permissions = permissions;
    }

    public LinkedHashSet<Menu> getMenus() {
        return menus;
    }

    public void setMenus(LinkedHashSet<Menu> menus) {
        this.menus = menus;
    }
}
