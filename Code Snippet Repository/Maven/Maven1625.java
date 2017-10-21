    protected void mergeNotifier_Configuration( Notifier target, Notifier source, boolean sourceDominant,
                                                Map<Object, Object> context )
    {
        Properties merged = new Properties();
        if ( sourceDominant )
        {
            merged.putAll( target.getConfiguration() );
            merged.putAll( source.getConfiguration() );
        }
        else
        {
            merged.putAll( source.getConfiguration() );
            merged.putAll( target.getConfiguration() );
        }
        target.setConfiguration( merged );
    }
