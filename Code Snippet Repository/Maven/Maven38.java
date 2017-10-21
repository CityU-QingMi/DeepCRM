    public static void main( String... args )
    {
        System.out.println( "Display parameters as parsed by Maven (in canonical form) and comparison result:" );
        if ( args.length == 0 )
        {
            return;
        }

        ComparableVersion prev = null;
        int i = 1;
        for ( String version : args )
        {
            ComparableVersion c = new ComparableVersion( version );

            if ( prev != null )
            {
                int compare = prev.compareTo( c );
                System.out.println( "   " + prev.toString() + ' '
                    + ( ( compare == 0 ) ? "==" : ( ( compare < 0 ) ? "<" : ">" ) ) + ' ' + version );
            }

            System.out.println( String.valueOf( i++ ) + ". " + version + " == " + c.getCanonical() );

            prev = c;
        }
    }
