    public static Slf4jConfiguration getConfiguration( ILoggerFactory loggerFactory )
    {
        Map<URL, Set<Object>> supported = new LinkedHashMap<>();

        String slf4jBinding = loggerFactory.getClass().getCanonicalName();

        try
        {
            Enumeration<URL> resources = Slf4jConfigurationFactory.class.getClassLoader().getResources( RESOURCE );

            while ( resources.hasMoreElements() )
            {
                URL resource = resources.nextElement();

                Properties conf = PropertyUtils.loadProperties( resource.openStream() );

                String impl = conf.getProperty( slf4jBinding );

                if ( impl != null )
                {
                    return (Slf4jConfiguration) Class.forName( impl ).newInstance();
                }

                supported.put( resource, conf.keySet() );
            }
        }
        catch ( IOException | ClassNotFoundException | IllegalAccessException | InstantiationException e )
        {
            e.printStackTrace();
        }

        return new UnsupportedSlf4jBindingConfiguration( slf4jBinding, supported );
    }
