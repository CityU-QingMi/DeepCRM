    private void validateConfig() throws ServletException
    {
        for (String initParameterName : Collections.list(getServletConfig().getInitParameterNames()))
        {
            if (FORBIDDEN_CONFIG_PARAMETERS.contains(initParameterName))
            {
                throw new UnavailableException(initParameterName + " not supported in " + getClass().getName());
            }
        }
    }
