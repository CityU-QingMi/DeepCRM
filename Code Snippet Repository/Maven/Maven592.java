    private String getMessage( String message, Throwable exception )
    {
        String fullMessage = ( message != null ) ? message : "";

        for ( Throwable t = exception; t != null; t = t.getCause() )
        {
            String exceptionMessage = t.getMessage();

            if ( t instanceof AbstractMojoExecutionException )
            {
                String longMessage = ( (AbstractMojoExecutionException) t ).getLongMessage();
                if ( StringUtils.isNotEmpty( longMessage ) )
                {
                    if ( StringUtils.isEmpty( exceptionMessage ) || longMessage.contains( exceptionMessage ) )
                    {
                        exceptionMessage = longMessage;
                    }
                    else if ( !exceptionMessage.contains( longMessage ) )
                    {
                        exceptionMessage = join( exceptionMessage, '\n' + longMessage );
                    }
                }
            }

            if ( StringUtils.isEmpty( exceptionMessage ) )
            {
                exceptionMessage = t.getClass().getSimpleName();
            }

            if ( t instanceof UnknownHostException && !fullMessage.contains( "host" ) )
            {
                fullMessage = join( fullMessage, "Unknown host " + exceptionMessage );
            }
            else if ( !fullMessage.contains( exceptionMessage ) )
            {
                fullMessage = join( fullMessage, exceptionMessage );
            }
        }

        return fullMessage.trim();
    }
