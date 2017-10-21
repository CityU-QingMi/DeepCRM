    private Properties toProperties( Map<String, String> dominant, Map<String, String> recessive )
    {
        Properties props = new Properties();
        if ( recessive != null )
        {
            props.putAll( recessive );
        }
        if ( dominant != null )
        {
            props.putAll( dominant );
        }
        return props;
    }
