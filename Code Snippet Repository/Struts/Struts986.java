    protected VelocityEngine newVelocityEngine(ServletContext context) {
        if (context == null) {
            String gripe = "Error attempting to create a new VelocityEngine from a null ServletContext!";
            LOG.error(gripe);
            throw new IllegalArgumentException(gripe);
        }

        Properties p = loadConfiguration(context);

        VelocityEngine velocityEngine = new VelocityEngine();

        //  Set the velocity attribute for the servlet context
        //  if this is not set the webapp loader WILL NOT WORK
        velocityEngine.setApplicationAttribute(ServletContext.class.getName(),
                context);

        try {
            velocityEngine.init(p);
        } catch (Exception e) {
            throw new StrutsException("Unable to instantiate VelocityEngine!", e);
        }

        return velocityEngine;
    }
