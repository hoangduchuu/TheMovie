
package com.example.hoang.themovie.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Dates {

    @SerializedName("maximum")
    @Expose
    private String maximum;
    @SerializedName("minimum")
    @Expose
    private String minimum;

    /**
     * @return The maximum
     */
    public String getMaximum() {
        return maximum;
    }

    /**
     * @param maximum The maximum
     */
    public void setMaximum(String maximum) {
        this.maximum = maximum;
    }

    /**
     * @return The minimum
     */
    public String getMinimum() {
        return minimum;
    }

    /**
     * @param minimum The minimum
     */
    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

}
