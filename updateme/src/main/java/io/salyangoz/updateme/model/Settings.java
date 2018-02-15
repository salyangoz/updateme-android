package io.salyangoz.updateme.model;

import java.io.Serializable;

/**
 * Created by Salyangoz.Co on 29/07/2017.
 */

public class Settings implements Serializable {

    private String title;
    private String message;
    private String updateUrl;
    private Boolean updateNeeeded;

    public Settings(String title, String description, String updateUrl,Boolean updateNeeeded) {

        this.title = title;
        this.message = description;
        this.updateUrl = updateUrl;
        this.updateNeeeded = updateNeeeded;
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

    public Boolean getUpdateNeeeded() {

        return updateNeeeded;
    }

    public void setUpdateNeeeded(Boolean updateNeeeded) {

        this.updateNeeeded = updateNeeeded;
    }
}
