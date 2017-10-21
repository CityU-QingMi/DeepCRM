    public static void initContext(ContextHandler handler) throws NamingException
    {
        // Add context specific weld container reference.
        // See https://issues.jboss.org/browse/WELD-1710
        // and https://github.com/weld/core/blob/2.2.5.Final/environments/servlet/core/src/main/java/org/jboss/weld/environment/servlet/WeldServletLifecycle.java#L244-L253
        handler.setInitParameter("org.jboss.weld.environment.container.class","org.jboss.weld.environment.jetty.JettyContainer");

        // Setup Weld BeanManager reference
        Reference ref = new Reference("javax.enterprise.inject.spi.BeanManager","org.jboss.weld.resources.ManagerObjectFactory",null);
        new Resource(handler,"BeanManager",ref);
    }
