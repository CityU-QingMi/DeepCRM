    private boolean generateAllowedOrigins(List<String> allowedOriginStore, String allowedOriginsConfig, String defaultOrigin) 
    {
        if (allowedOriginsConfig == null)
            allowedOriginsConfig = defaultOrigin;
        String[] allowedOrigins = StringUtil.csvSplit(allowedOriginsConfig);
        for (String allowedOrigin : allowedOrigins)
        {
            if (allowedOrigin.length() > 0)
            {
                if (ANY_ORIGIN.equals(allowedOrigin))
                {
                    allowedOriginStore.clear();
                    return true;
                }
                else
                {
                    allowedOriginStore.add(allowedOrigin);
                }
            }
        }
        return false;
    }
