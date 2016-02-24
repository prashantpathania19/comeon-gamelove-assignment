/**
 * 
 */
package com.comeon.assignment;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

/**
 * Configuration class for Database
 */
public class DatabaseConfiguration extends Configuration {
    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

    /**
     * getter to return DataSourceFactory
     * @return - DataSourceFactory
     */
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }
}
