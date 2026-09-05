package dto;

import enums.UserCategory;


public class User {
    String id;
    String name;
    UserCategory category;

    public User(String userId, String userName , UserCategory userCategory) {
        this.id = userId;
        name = userName;
        category = userCategory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserCategory getCategory() {
        return category;
    }

    public void setCategory(UserCategory category) {
        this.category = category;
    }
}
