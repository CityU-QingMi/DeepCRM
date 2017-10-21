    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        ServletRegistration defaultRego = sce.getServletContext().getServletRegistration("default");
        Collection<String> mappings = defaultRego.getMappings();
        assertTrue(mappings.contains("/"));
        
        Set<String> otherMappings = sce.getServletContext().getServletRegistration("foo").addMapping("/");
        assertTrue(otherMappings.isEmpty());
        Collection<String> fooMappings = sce.getServletContext().getServletRegistration("foo").getMappings();
        assertTrue(fooMappings.contains("/"));
    }
