    protected void mergePluginExecution_Phase( PluginExecution target, PluginExecution source, boolean sourceDominant,
                                               Map<Object, Object> context )
    {
        String src = source.getPhase();
        if ( src != null )
        {
            if ( sourceDominant || target.getPhase() == null )
            {
                target.setPhase( src );
                target.setLocation( "phase", source.getLocation( "phase" ) );
            }
        }
    }
