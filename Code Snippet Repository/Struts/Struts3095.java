    protected static String getOption( ServletConfig config, String name, String defaultV) {
        if( config == null ) return defaultV;

        String value=config.getInitParameter(name);
        if( value != null ) return value;
        if( config.getServletContext() ==null )
            return defaultV;
        value=config.getServletContext().getInitParameter(name);
        if( value!=null ) return value;
        return defaultV;
    }
