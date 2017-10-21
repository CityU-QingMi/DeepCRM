    private static Integer getNextIntegerToken( StringTokenizer tok )
    {
        try
        {
            String s = tok.nextToken();
            if ( ( s.length() > 1 ) && s.startsWith( "0" ) )
            {
                throw new NumberFormatException( "Number part has a leading 0: '" + s + "'" );
            }
            return Integer.valueOf( s );
        }
        catch ( NoSuchElementException e )
        {
            throw new NumberFormatException( "Number is invalid" );
        }
    }
