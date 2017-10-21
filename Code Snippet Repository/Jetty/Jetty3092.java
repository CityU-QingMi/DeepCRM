    @Override
    public String getWelcomeFile(String pathInContext)
    {
        if (_welcomes == null)
            return null;

        for (int i = 0; i < _welcomes.length; i++)
        {
            String welcome_in_context = URIUtil.addPaths(pathInContext,_welcomes[i]);
            Resource welcome = getResource(welcome_in_context);
            if (welcome != null && welcome.exists())
                return welcome_in_context;
        }
        // not found
        return null;
    }
