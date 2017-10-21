    private static String format( List<Parameter> parameters )
    {
        StringBuilder buffer = new StringBuilder( 128 );
        if ( parameters != null )
        {
            for ( Parameter parameter : parameters )
            {
                if ( buffer.length() > 0 )
                {
                    buffer.append( ", " );
                }
                buffer.append( '\'' ).append( parameter.getName() ).append( '\'' );
            }
        }
        return buffer.toString();
    }
