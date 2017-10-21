    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException
    {
        // Directly annotated with @ManagedObject
        CommonComponent common = new CommonComponent();
        LOG.info("Initializing " + common.getClass().getName());
        ctx.setAttribute("org.eclipse.jetty.test.jmx.common",common);
        
        // Indirectly managed via a MBean
        ctx.setAttribute("org.eclipse.jetty.test.jmx.ping",new Pinger());
        ctx.setAttribute("org.eclipse.jetty.test.jmx.echo",new Echoer());
    }
