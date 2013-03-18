package com.sneaky.stratagem.guice;

import com.google.inject.servlet.ServletModule;
import com.rogue.stratagem.web.server.RegisterServlet;

public class MyServletModule extends ServletModule {
    @Override
    protected void configureServlets() {
        serve("/*").with(RegisterServlet.class);
    }
}
