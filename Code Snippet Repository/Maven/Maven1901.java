    public Logger getLogger( String name )
    {
        Logger simpleLogger = loggerMap.get( name );
        if ( simpleLogger != null )
        {
            return simpleLogger;
        }
        else
        {
            Logger newInstance = new MavenSimpleLogger( name );
            Logger oldInstance = loggerMap.putIfAbsent( name, newInstance );
            return oldInstance == null ? newInstance : oldInstance;
        }
    }
