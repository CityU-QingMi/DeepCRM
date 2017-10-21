    private static String getMessage( Throwable error, String def )
    {
        if ( error == null )
        {
            return def;
        }
        String msg = error.getMessage();
        if ( StringUtils.isNotEmpty( msg ) )
        {
            return msg;
        }
        return getMessage( error.getCause(), def );
    }
