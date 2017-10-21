    private boolean determineNameMatch( String name )
    {
        String test = name;
        boolean reverse = false;

        if ( test.startsWith( "!" ) )
        {
            reverse = true;
            test = test.substring( 1 );
        }

        boolean result = Os.isName( test );

        if ( reverse )
        {
            return !result;
        }
        else
        {
            return result;
        }
    }
