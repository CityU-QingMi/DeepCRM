    private boolean isNoteworthyException( Throwable exception )
    {
        if ( exception == null )
        {
            return false;
        }
        else if ( exception instanceof Error )
        {
            return true;
        }
        else if ( exception instanceof RuntimeException )
        {
            return false;
        }
        else if ( exception.getClass().getName().startsWith( "java" ) )
        {
            return false;
        }
        return true;
    }
