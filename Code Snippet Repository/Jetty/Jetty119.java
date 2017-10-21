    public void apply()
    {
        Class<? extends java.util.EventListener> clazz = (Class<? extends EventListener>)getTargetClass();

        if (clazz == null)
        {
            LOG.warn(_className+" cannot be loaded");
            return;
        }

        try
        {
            if (ServletContextListener.class.isAssignableFrom(clazz) ||
                    ServletContextAttributeListener.class.isAssignableFrom(clazz) ||
                    ServletRequestListener.class.isAssignableFrom(clazz) ||
                    ServletRequestAttributeListener.class.isAssignableFrom(clazz) ||
                    HttpSessionListener.class.isAssignableFrom(clazz) ||
                    HttpSessionAttributeListener.class.isAssignableFrom(clazz) ||
                    HttpSessionIdListener.class.isAssignableFrom(clazz))
            {
                java.util.EventListener listener = (java.util.EventListener)_context.getServletContext().createInstance(clazz);      
                MetaData metaData = _context.getMetaData();
                if (metaData.getOrigin(clazz.getName()+".listener") == Origin.NotSet)
                {
                    ListenerHolder h = _context.getServletHandler().newListenerHolder(new Source(Source.Origin.ANNOTATION, clazz.getName()));
                    h.setListener(listener);
                    _context.getServletHandler().addListener(h);
                    _context.addEventListener(listener);
                }
            }
            else
                LOG.warn(clazz.getName()+" does not implement one of the servlet listener interfaces");
        }
        catch (Exception e)
        {
            LOG.warn(e);
        }
    }
