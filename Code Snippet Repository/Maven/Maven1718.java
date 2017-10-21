    protected void mergeDependency_SystemPath( Dependency target, Dependency source, boolean sourceDominant,
                                               Map<Object, Object> context )
    {
        String src = source.getSystemPath();
        if ( src != null )
        {
            if ( sourceDominant || target.getSystemPath() == null )
            {
                target.setSystemPath( src );
                target.setLocation( "systemPath", source.getLocation( "systemPath" ) );
            }
        }
    }
