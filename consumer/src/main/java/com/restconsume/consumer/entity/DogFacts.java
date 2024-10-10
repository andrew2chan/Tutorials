package com.restconsume.consumer.entity;

import java.util.List;

public class DogFacts {
    private List<String> facts;
    private boolean success;

    public DogFacts() {
    }

    public DogFacts(List<String> facts, boolean success) {
        this.facts = facts;
        this.success = success;
    }

    public List<String> getFacts() {
        return facts;
    }

    public void setFacts(List<String> facts) {
        this.facts = facts;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "DogFacts{" +
                "facts=" + facts +
                ", success=" + success +
                '}';
    }
}
