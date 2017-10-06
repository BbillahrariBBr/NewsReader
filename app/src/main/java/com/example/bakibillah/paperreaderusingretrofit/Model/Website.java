package com.example.bakibillah.paperreaderusingretrofit.Model;

import java.util.List;

/**
 * Created by BakiBillah on 10/6/2017.
 */

public class Website {
    private String Status;
    private List<Source>sources;

    public Website() {

    }

    public Website(String status, List<Source> sources) {
        this.Status = status;
        this.sources = sources;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }
}
