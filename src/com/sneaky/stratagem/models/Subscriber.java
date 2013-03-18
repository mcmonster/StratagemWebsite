package com.sneaky.stratagem.models;

import com.google.appengine.api.datastore.Key;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Subscriber { 
    @Expose
    @Persistent
    private String email;
    
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
    
    public Subscriber() {}
    
    public Subscriber(final String email) {
        this.email = email;
    }
    
    public void deserialize(final String internalState) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Subscriber source = gson.fromJson(internalState, this.getClass());
        
        this.email = source.email;
    }
}
