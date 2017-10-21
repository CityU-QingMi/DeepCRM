    public void initCDI()
    {
        Class cdiInitializer = null;
        try
        {
            cdiInitializer = Thread.currentThread().getContextClassLoader().loadClass("org.eclipse.jetty.cdi.servlet.JettyWeldInitializer");
            Method initWebAppMethod = cdiInitializer.getMethod("initWebApp", new Class[]{WebAppContext.class});
            initWebAppMethod.invoke(null, new Object[]{this});
        }
        catch (ClassNotFoundException e)
        {
            LOG.debug("o.e.j.cdi.servlet.JettyWeldInitializer not found, no cdi integration available");
        }
        catch (NoSuchMethodException e)
        {
            LOG.warn("o.e.j.cdi.servlet.JettyWeldInitializer.initWebApp() not found, no cdi integration available");
        }
        catch (Exception e)
        {
           LOG.warn("Problem initializing cdi", e);
        }
    }
