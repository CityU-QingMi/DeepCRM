    protected void mergeReporting_ExcludeDefaults( Reporting target, Reporting source, boolean sourceDominant,
                                                   Map<Object, Object> context )
    {
        String src = source.getExcludeDefaults();
        if ( src != null )
        {
            if ( sourceDominant || target.getExcludeDefaults() == null )
            {
                target.setExcludeDefaults( src );
                target.setLocation( "excludeDefaults", source.getLocation( "excludeDefaults" ) );
            }
        }
    }
