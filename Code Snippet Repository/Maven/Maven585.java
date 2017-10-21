    public void onEvent( Object event )
    {
        if ( eventSpies.isEmpty() )
        {
            return;
        }
        for ( EventSpy eventSpy : eventSpies )
        {
            try
            {
                eventSpy.onEvent( event );
            }
            catch ( Exception | LinkageError e )
            {
                logError( "notify", e, eventSpy );
            }
        }
    }
