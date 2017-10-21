    public static Properties copyProperties( Properties properties )
    {
        final Properties copyProperties = new Properties();
        // guard against modification/removal of keys in the given properties (MNG-5670, MNG-6053, MNG-6105)
        synchronized ( properties )
        {
            copyProperties.putAll( properties );
        }
        return copyProperties;
    }
