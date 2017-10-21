    @Override
    public void contextInitialized(ServletContextEvent sce)
    {	
        // System.err.println("contextInitialized "+sce);
        _called.put("contextInitialized",new Throwable());

        //configure programmatic security
        ServletRegistration.Dynamic rego = sce.getServletContext().addServlet("RegoTest", RegTest.class.getName());
        rego.addMapping("/rego/*");
        HttpConstraintElement constraintElement = new HttpConstraintElement(ServletSecurity.EmptyRoleSemantic.PERMIT, 
            ServletSecurity.TransportGuarantee.NONE, new String[]{"admin"});
        ServletSecurityElement securityElement = new ServletSecurityElement(constraintElement, null);
        Set<String> unchanged = rego.setServletSecurity(securityElement);
        //// System.err.println("Security constraints registered: "+unchanged.isEmpty());

        //Test that a security constraint from web.xml can't be overridden programmatically
        ServletRegistration.Dynamic rego2 = sce.getServletContext().addServlet("RegoTest2", RegTest.class.getName());
        rego2.addMapping("/rego2/*");
        securityElement = new ServletSecurityElement(constraintElement, null);
        unchanged = rego2.setServletSecurity(securityElement);
        //// System.err.println("Overridding web.xml constraints not possible:" +!unchanged.isEmpty());

        /* For servlet 3.0 */
        FilterRegistration registration = sce.getServletContext().addFilter("TestFilter",TestFilter.class.getName());
        if (registration != null) //otherwise defined in web.xml
        {
            ((FilterRegistration.Dynamic)registration).setAsyncSupported(true);
        }
        else
        {
            registration=sce.getServletContext().getFilterRegistration("TestFilter");
        }
        registration.setInitParameter("remote", "false");
        registration.addMappingForUrlPatterns(
                EnumSet.of(DispatcherType.ERROR,DispatcherType.ASYNC,DispatcherType.FORWARD,DispatcherType.INCLUDE,DispatcherType.REQUEST),
                true, 
                new String[]{"/*"});
    }
