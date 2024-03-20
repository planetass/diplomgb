package ru.gb.webservice.enums;


public enum RolePage {
    ADMIN("admin-page","car-models"),
    MODERATOR("admin-page","car-models"),
    USER("user-page","user-page"),
    ANONIME("signin","signin");
        private final String startPage;
        private final String carModelPage;

    RolePage(String startPage,String carModelPage) {
        this.startPage = startPage;
        this.carModelPage = carModelPage;
    }
    public String getStartPage() {
        return startPage;
    }

    public String getCarModelPage() {
        return carModelPage;
    }
}
