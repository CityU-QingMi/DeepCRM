    public WebAppContext createWebAppContext() throws MalformedURLException, IOException
    {
        WebAppContext context = new WebAppContext();
        context.setContextPath(this.contextPath);
        context.setBaseResource(Resource.newResource(this.contextDir));
        context.setAttribute("org.eclipse.jetty.websocket.jsr356",Boolean.TRUE);

        // @formatter:off
        context.setConfigurations(new Configuration[] {
                new AnnotationConfiguration(),
                new WebXmlConfiguration(),
                new WebInfConfiguration(),
                new PlusConfiguration(), 
                new MetaInfConfiguration(),
                new FragmentConfiguration(), 
                new EnvConfiguration()});
        // @formatter:on

        return context;
    }
