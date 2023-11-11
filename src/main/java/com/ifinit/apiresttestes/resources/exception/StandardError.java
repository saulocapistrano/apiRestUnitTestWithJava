package com.ifinit.apiresttestes.resources.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class StandardError {



    private LocalDateTime timestemp;
    private Integer status;
    private String error;
    private String path;


    public LocalDateTime getTimestemp() {
        return timestemp;
    }
    public void setTimestemp(LocalDateTime timestemp) {
        this.timestemp = timestemp;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }


}

