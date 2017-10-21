    private String join( String message1, String message2 )
    {
        String message = "";

        if ( StringUtils.isNotEmpty( message1 ) )
        {
            message = message1.trim();
        }

        if ( StringUtils.isNotEmpty( message2 ) )
        {
            if ( StringUtils.isNotEmpty( message ) )
            {
                if ( message.endsWith( "." ) || message.endsWith( "!" ) || message.endsWith( ":" ) )
                {
                    message += " ";
                }
                else
                {
                    message += ": ";
                }
            }

            message += message2;
        }

        return message;
    }
