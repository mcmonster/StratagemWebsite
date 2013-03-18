package com.sneaky.stratagem.models;

import java.util.Map;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.fasterxml.jackson.databind.ObjectMapper;

@PersistenceCapable
public class Helper {
    @Persistent
    private boolean art;
    
    @Persistent
    private boolean development;
    
    @Persistent
    private boolean donating;
    
    @Persistent
    private String email;
    
    @Persistent
    private boolean investing;
    
    @Persistent
    private boolean marketing;
    
    @Persistent
    private String name;
    
    @Persistent
    private String note;
    
    @Persistent
    private boolean other;
    
    @Persistent
    private boolean testing;
    
    @Persistent
    private boolean website;
    
    public final void deserialize(final String internalState) {
        try {
            Map<String, Object> map = new ObjectMapper().readValue(internalState, Map.class);
            
            email = (String) map.get("email");
            name = (String) map.get("name");
            note = (String) map.get("note");
            
            String art = (String) map.get("art");
            if (art.equalsIgnoreCase("TRUE")) {
                this.art = true;
            } else {
                this.art = false;
            }
            
            String development = (String) map.get("development");
            if (development.equalsIgnoreCase("TRUE")) {
                this.development = true;
            } else {
                this.development = false;
            }
            
            String donating = (String) map.get("donating");
            if (donating.equalsIgnoreCase("TRUE")) {
                this.donating = true;
            } else {
                this.donating = false;
            }
            
            String investing = (String) map.get("investing");
            if (investing.equalsIgnoreCase("TRUE")) {
                this.investing = true;
            } else {
                this.investing = false;
            }
            
            String marketing = (String) map.get("marketing");
            if (marketing.equalsIgnoreCase("TRUE")) {
                this.marketing = true;
            } else {
                this.marketing = false;
            }
            
            String other = (String) map.get("other");
            if (other.equalsIgnoreCase("TRUE")) {
                this.other = true;
            } else {
                this.other = false;
            }
            
            String testing = (String) map.get("testing");
            if (testing.equalsIgnoreCase("TRUE")) {
                this.testing = true;
            } else { 
                this.testing = false;
            }
            
            String website = (String) map.get("website");
            if (website.equalsIgnoreCase("TRUE")) {
                this.website = true;
            } else {
                this.website = false;
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
