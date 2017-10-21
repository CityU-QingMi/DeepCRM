    public void setContextValue( String key, Object value )
    {
        if ( context == null )
        {
            context = new HashMap<>();
        }
        if ( value != null )
        {
            context.put( key, value );
        }
        else
        {
            context.remove( key );
        }
    }
