package com.salyangoz.updateme.model;

import java.io.Serializable;

/**
 * Created by Salyangoz.Co on 29/07/2017.
 */

public class Settings implements Serializable {

    private String title;
    private String message;
    private String updateUrl;

    public Settings(String title, String description, String updateUrl) {

        this.title = title;
        this.message = description;
        this.updateUrl = updateUrl;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }

    public String getUpdateUrl() {

        return updateUrl;
    }

    public void setUpdateUrl(String updateUrl) {

        this.updateUrl = updateUrl;
    }
}
