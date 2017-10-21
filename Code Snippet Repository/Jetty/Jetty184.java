    public static void initWebApp(WebAppContext webapp) throws NamingException
    {
        initContext(webapp);

        // webapp cannot change / replace weld classes
        webapp.addSystemClass("org.jboss.weld.");
        webapp.addSystemClass("org.jboss.classfilewriter.");
        webapp.addSystemClass("org.jboss.logging.");
        webapp.addSystemClass("com.google.common.");
        webapp.addSystemClass("org.eclipse.jetty.cdi.websocket.annotation.");
        

        // don't hide weld classes from webapps (allow webapp to use ones from system classloader)
        webapp.prependServerClass("-org.eclipse.jetty.cdi.websocket.annotation.");
        webapp.prependServerClass("-org.eclipse.jetty.cdi.core.");
        webapp.prependServerClass("-org.eclipse.jetty.cdi.servlet.");
        webapp.addServerClass("-org.jboss.weld.");
        webapp.addServerClass("-org.jboss.classfilewriter.");
        webapp.addServerClass("-org.jboss.logging.");
        webapp.addServerClass("-com.google.common.");
    
    }
