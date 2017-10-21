    private Map<String, String> toMap( Properties properties )
    {
        if ( properties == null )
        {
            return Collections.emptyMap();
        }
        Map<String, String> map = new HashMap<>();
        Enumeration keys = properties.keys();
        while ( keys.hasMoreElements() )
        {
            String key = (String) keys.nextElement();
            map.put( key, properties.getProperty( key ) );
        }
        return map;
    }
