    public boolean isJspAvailable()
    {
        try
        {
            getClass().getClassLoader().loadClass("org.apache.jasper.servlet.JspServlet");
        }
        catch (Exception e)
        {
            LOG.warn("Unable to locate the JspServlet: jsp support unavailable.", e);
            return false;
        }
        return true;
    }
