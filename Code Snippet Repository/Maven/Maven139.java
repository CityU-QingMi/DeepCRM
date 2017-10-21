    private boolean determineVersionMatch( String version )
    {
        String test = version;
        boolean reverse = false;

        if ( test.startsWith( "!" ) )
        {
            reverse = true;
            test = test.substring( 1 );
        }

        boolean result = Os.isVersion( test );

        if ( reverse )
        {
            return !result;
        }
        else
        {
            return result;
        }
    }
