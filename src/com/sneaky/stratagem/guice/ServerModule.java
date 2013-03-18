package com.sneaky.stratagem.guice;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class ServerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(PersistenceManagerFactory.class)
                .toInstance(JDOHelper.getPersistenceManagerFactory("transactions-optional"));
    }
    
    @Provides
    private PersistenceManager providePersistenceManager(PersistenceManagerFactory factory) {
        return factory.getPersistenceManager();
    }
}
