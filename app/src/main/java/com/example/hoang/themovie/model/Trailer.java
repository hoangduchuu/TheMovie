
package com.example.hoang.themovie.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Trailer {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("results")
    @Expose
    private List<ResultTrailer> results = new ArrayList<ResultTrailer>();

    /**
     * @return The id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return The results
     */
    public List<ResultTrailer> getResults() {
        return results;
    }

    /**
     * @param results The results
     */
    public void setResults(List<ResultTrailer> results) {
        this.results = results;
    }

}
