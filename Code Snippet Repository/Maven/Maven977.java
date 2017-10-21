    public static void addEnvVars( Properties props )
    {
        if ( props != null )
        {
            if ( envVars == null )
            {
                Properties tmp = new Properties();
                boolean caseSensitive = !Os.isFamily( Os.FAMILY_WINDOWS );
                for ( Map.Entry<String, String> entry : System.getenv().entrySet() )
                {
                    String key =
                        "env." + ( caseSensitive ? entry.getKey() : entry.getKey().toUpperCase( Locale.ENGLISH ) );
                    tmp.setProperty( key, entry.getValue() );
                }
                envVars = tmp;
            }

            props.putAll( envVars );
        }
    }
