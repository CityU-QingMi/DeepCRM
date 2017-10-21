    private static String toMessage( String message, List<String> errors )
    {
        StringBuilder buffer = new StringBuilder( 256 );
        buffer.append( message );
        for ( String error : errors )
        {
            buffer.append( ", " ).append( error );
        }
        return buffer.toString();
    }
