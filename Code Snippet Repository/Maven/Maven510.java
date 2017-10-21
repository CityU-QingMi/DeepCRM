    private static String protocol( final String url )
    {
        final int pos = url.indexOf( ':' );

        if ( pos == -1 )
        {
            return "";
        }
        return url.substring( 0, pos ).trim();
    }
