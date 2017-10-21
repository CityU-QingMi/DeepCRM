    public boolean supportsPreDestroy (Class c)
    {
        if (javax.servlet.Servlet.class.isAssignableFrom(c) ||
                javax.servlet.Filter.class.isAssignableFrom(c) || 
                javax.servlet.ServletContextListener.class.isAssignableFrom(c) ||
                javax.servlet.ServletContextAttributeListener.class.isAssignableFrom(c) ||
                javax.servlet.ServletRequestListener.class.isAssignableFrom(c) ||
                javax.servlet.ServletRequestAttributeListener.class.isAssignableFrom(c) ||
                javax.servlet.http.HttpSessionListener.class.isAssignableFrom(c) ||
                javax.servlet.http.HttpSessionAttributeListener.class.isAssignableFrom(c) ||
                javax.servlet.http.HttpSessionIdListener.class.isAssignableFrom(c) ||
                javax.servlet.AsyncListener.class.isAssignableFrom(c) ||
                javax.servlet.http.HttpUpgradeHandler.class.isAssignableFrom(c))
            return true;
        
        return false;
    }
