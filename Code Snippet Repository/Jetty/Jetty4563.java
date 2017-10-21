    public void setProperty(String key, String value, String source)
    {
        // Special / Prevent override from start.ini's
        if (key.equals("jetty.home"))
        {
            properties.setProperty("jetty.home",System.getProperty("jetty.home"),source);
            return;
        }

        // Special / Prevent override from start.ini's
        if (key.equals("jetty.base"))
        {
            properties.setProperty("jetty.base",System.getProperty("jetty.base"),source);
            return;
        }

        properties.setProperty(key,value,source);
        if (key.equals("java.version"))
        {
            try
            {
                JavaVersion ver = JavaVersion.parse(value);
                properties.setProperty("java.version",ver.getVersion(),source);
                properties.setProperty("java.version.platform",Integer.toString(ver.getPlatform()),source);
                properties.setProperty("java.version.major",Integer.toString(ver.getMajor()),source);
                properties.setProperty("java.version.minor",Integer.toString(ver.getMinor()),source);
                properties.setProperty("java.version.micro",Integer.toString(ver.getMicro()),source);
                properties.setProperty("java.version.update",Integer.toString(ver.getUpdate()),source);
            }
            catch (Throwable x)
            {
                throw new UsageException(UsageException.ERR_BAD_ARG, x.getMessage());
            }
        }
    }
