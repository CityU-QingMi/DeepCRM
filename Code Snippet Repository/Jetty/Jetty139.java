    private WebAppContext makeWebAppContext (String className, String servletName, String[] paths)
    {
        WebAppContext wac = new WebAppContext();

        ServletHolder[] holders = new ServletHolder[1];
        holders[0] = new ServletHolder();
        holders[0].setClassName(className);
        holders[0].setName(servletName);
        holders[0].setServletHandler(wac.getServletHandler());
        wac.getServletHandler().setServlets(holders);
        wac.setSecurityHandler(new ConstraintSecurityHandler());

        ServletMapping[] servletMappings = new ServletMapping[1];
        servletMappings[0] = new ServletMapping();

        servletMappings[0].setPathSpecs(paths);
        servletMappings[0].setServletName(servletName);
        wac.getServletHandler().setServletMappings(servletMappings);
        return wac;
    }
