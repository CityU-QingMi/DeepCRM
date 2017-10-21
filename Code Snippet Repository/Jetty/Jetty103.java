    public void doHandle(Class clazz)
    {
        if (!Servlet.class.isAssignableFrom(clazz))
            return; //only applicable on javax.servlet.Servlet derivatives

        if (!(_context.getSecurityHandler() instanceof ConstraintAware))
        {
            LOG.warn("SecurityHandler not ConstraintAware, skipping security annotation processing");
            return;
        }

        DeclareRoles declareRoles = (DeclareRoles) clazz.getAnnotation(DeclareRoles.class);
        if (declareRoles == null)
            return;

        String[] roles = declareRoles.value();

        if (roles != null && roles.length > 0)
        {
            for (String r:roles)
                ((ConstraintSecurityHandler)_context.getSecurityHandler()).addRole(r);
        }
    }
