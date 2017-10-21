    protected Logger createLogger()
    {
        String servletName = getServletConfig().getServletName();
        servletName = servletName.replace('-', '.');
        if ((getClass().getPackage() != null) && !servletName.startsWith(getClass().getPackage().getName()))
        {
            servletName = getClass().getName() + "." + servletName;
        }
        return Log.getLogger(servletName);
    }
