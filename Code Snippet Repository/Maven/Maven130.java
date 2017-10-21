    public String toString()
    {
        StringBuilder buffer = new StringBuilder( "ManagedVersionMap (" + size() + " entries)\n" );
        Iterator<String> iter = keySet().iterator();
        while ( iter.hasNext() )
        {
            String key = iter.next();
            buffer.append( key ).append( '=' ).append( get( key ) );
            if ( iter.hasNext() )
            {
                buffer.append( '\n' );
            }
        }
        return buffer.toString();
    }
