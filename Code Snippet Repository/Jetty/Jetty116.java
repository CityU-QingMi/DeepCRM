    public void doHandle(Class clazz)
    {
        if (!(_context.getSecurityHandler() instanceof ConstraintAware))
        {
            LOG.warn("SecurityHandler not ConstraintAware, skipping security annotation processing");
            return;
        }

       ServletSecurity servletSecurity = (ServletSecurity)clazz.getAnnotation(ServletSecurity.class);
       if (servletSecurity == null)
           return;

       //If there are already constraints defined (ie from web.xml) that match any 
       //of the url patterns defined for this servlet, then skip the security annotation.

       List<ServletMapping> servletMappings = getServletMappings(clazz.getCanonicalName());
       List<ConstraintMapping> constraintMappings =  ((ConstraintAware)_context.getSecurityHandler()).getConstraintMappings();

       if (constraintsExist(servletMappings, constraintMappings))
       {
           LOG.warn("Constraints already defined for "+clazz.getName()+", skipping ServletSecurity annotation");
           return;
       }

       //Make a fresh list
       constraintMappings = new ArrayList<ConstraintMapping>();

       ServletSecurityElement securityElement = new ServletSecurityElement(servletSecurity);
       for (ServletMapping sm : servletMappings)
       {
           for (String url : sm.getPathSpecs())
           {
               _context.getMetaData().setOrigin("constraint.url."+url,servletSecurity,clazz);
               constraintMappings.addAll(ConstraintSecurityHandler.createConstraintsWithMappingsForPath(clazz.getName(), url, securityElement));
           }
       }

       //set up the security constraints produced by the annotation
       ConstraintAware securityHandler = (ConstraintAware)_context.getSecurityHandler();

       for (ConstraintMapping m:constraintMappings)
           securityHandler.addConstraintMapping(m);
       
       //Servlet Spec 3.1 requires paths with uncovered http methods to be reported
       securityHandler.checkPathsWithUncoveredHttpMethods();
    }
