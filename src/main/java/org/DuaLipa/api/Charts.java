package org.DuaLipa.api;

import java.util.ArrayList;
import java.util.List;

public class Charts {
    private String term;
    private List<Results> results;

    public Charts() {
        this.results = new ArrayList();
    }

    public Charts(String term, List<Results> data) {
        this.term = term;
        this.results = data;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results == null ? new ArrayList() : results;
    }
}
