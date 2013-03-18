package com.rogue.stratagem.web.server;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sneaky.stratagem.models.Helper;
import com.sneaky.stratagem.models.Subscriber;

@Singleton
public class RegisterServlet extends HttpServlet {
    private PersistenceManagerFactory databaseFactory;
    
    /** Serialization ID. */
    private static final long serialVersionUID = 5693435568300391344L;
    
    @Inject
    protected RegisterServlet(final PersistenceManagerFactory databaseFactory) {
        this.databaseFactory = databaseFactory;
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        
        // Determine the type of registration
        String type = request.getParameter("type").toString();
        String data = request.getParameter("data").toString();
        if (type.equalsIgnoreCase("KeepMeUpdated")) {
            registerSubscriber(data, response);
        } else if (type.equalsIgnoreCase("WantToHelp")) {
            registerHelper(data, response);
        } else {
            throw new ServletException("Unknown registration type!");
        }
        
        // Configuring the response encoding
        response.setCharacterEncoding("UTF-8");
    }
    
    private void registerHelper(String data, HttpServletResponse response) throws IOException {
        PersistenceManager database = databaseFactory.getPersistenceManager();
        
        try {    
            Helper helper = new Helper();
            helper.deserialize(data);
            database.makePersistent(helper);
            
            response.getWriter().append("Thanks offering to help! Expect to hear from us in the near future.");
        } finally {
            database.close();
        }
    }
    
    private void registerSubscriber(String data, HttpServletResponse response) throws IOException {
        PersistenceManager database = databaseFactory.getPersistenceManager();
        
        try {
            Subscriber subscriber = new Subscriber();
            subscriber.deserialize(data);
            database.makePersistent(subscriber);
            
            response.getWriter().append("Thanks for subscribing! Expect to hear from us in the near future.");
        } finally {
            database.close();
        }
    }
}
