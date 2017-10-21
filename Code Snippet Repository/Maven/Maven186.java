    public String render( String indentation )
    {
        if ( messages.size() == 0 )
        {
            return indentation + "There were no validation errors.";
        }

        StringBuilder message = new StringBuilder();

//        if ( messages.size() == 1 )
//        {
//            message.append( "There was 1 validation error: " );
//        }
//        else
//        {
//            message.append( "There was " + messages.size() + " validation errors: " + NEWLINE );
//        }
//
        for ( int i = 0; i < messages.size(); i++ )
        {
            message.append( indentation ).append( '[' ).append( i ).append( "]  " ).append( messages.get( i ) ).append(
                NEWLINE );
        }

        return message.toString();
    }
