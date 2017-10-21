    private boolean determineFamilyMatch( String family )
    {
        String test = family;
        boolean reverse = false;

        if ( test.startsWith( "!" ) )
        {
            reverse = true;
            test = test.substring( 1 );
        }

        boolean result = Os.isFamily( test );

        if ( reverse )
        {
            return !result;
        }
        else
        {
            return result;
        }
    }
