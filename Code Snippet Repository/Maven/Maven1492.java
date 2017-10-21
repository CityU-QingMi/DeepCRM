    private boolean determineArchMatch( String arch )
    {
        String test = arch;
        boolean reverse = false;

        if ( test.startsWith( "!" ) )
        {
            reverse = true;
            test = test.substring( 1 );
        }

        boolean result = Os.isArch( test );

        return reverse ? !result : result;
    }
