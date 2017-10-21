    public void init( EventSpy.Context context )
    {
        if ( eventSpies.isEmpty() )
        {
            return;
        }
        for ( EventSpy eventSpy : eventSpies )
        {
            try
            {
                eventSpy.init( context );
            }
            catch ( Exception | LinkageError e )
            {
                logError( "initialize", e, eventSpy );
            }
        }
    }
