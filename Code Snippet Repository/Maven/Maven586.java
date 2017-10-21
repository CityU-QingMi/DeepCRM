    public void close()
    {
        if ( eventSpies.isEmpty() )
        {
            return;
        }
        for ( EventSpy eventSpy : eventSpies )
        {
            try
            {
                eventSpy.close();
            }
            catch ( Exception | LinkageError e )
            {
                logError( "close", e, eventSpy );
            }
        }
    }
