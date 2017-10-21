    public boolean hasSystemProperties()
    {
        for (String key : systemPropertyKeys)
        {
            // ignored keys
            if ("jetty.home".equals(key) || "jetty.base".equals(key) || "main.class".equals(key))
            {
                // skip
                continue;
            }
            return true;
        }
        return false;
    }
