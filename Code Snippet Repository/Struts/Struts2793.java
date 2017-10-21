    boolean matchesOptionalSpacesFollowedBy( String s )
        throws JasperException
    {
        Mark mark = mark();

        skipSpaces();
        boolean result = matches( s );
        if( !result ) {
            reset( mark );
        }

        return result;
    }
