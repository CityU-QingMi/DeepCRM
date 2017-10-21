    protected String getLocation( final StackTraceElement e )
    {
        assert e != null;

        if ( e.isNativeMethod() )
        {
            return "Native Method";
        }
        else if ( e.getFileName() == null )
        {
            return "Unknown Source";
        }
        else if ( e.getLineNumber() >= 0 )
        {
            return String.format( "%s:%s", e.getFileName(), e.getLineNumber() );
        }
        else
        {
            return e.getFileName();
        }
    }
