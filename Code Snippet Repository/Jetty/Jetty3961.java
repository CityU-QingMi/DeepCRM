    public void contextDestroyed(ServletContextEvent sce)
    {
        try
        {
            //Check that the BeanELResolver class is on the classpath
            Class<?> beanELResolver = Loader.loadClass("javax.el.BeanELResolver");

            //Get a reference via reflection to the properties field which is holding class references
            Field field = getField(beanELResolver);

            //Get rid of references
            purgeEntries(field);

            if (LOG.isDebugEnabled())
                LOG.debug("javax.el.BeanELResolver purged");
        }

        catch (ClassNotFoundException e)
        {
            //BeanELResolver not on classpath, ignore
        }
        catch (SecurityException | IllegalArgumentException | IllegalAccessException e)
        {
            LOG.warn("Cannot purge classes from javax.el.BeanELResolver", e);
        }
        catch (NoSuchFieldException e)
        {
            LOG.debug("Not cleaning cached beans: no such field javax.el.BeanELResolver.properties");
        }

    }
