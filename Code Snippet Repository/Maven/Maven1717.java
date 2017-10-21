    protected void mergeDependency_Scope( Dependency target, Dependency source, boolean sourceDominant,
                                          Map<Object, Object> context )
    {
        String src = source.getScope();
        if ( src != null )
        {
            if ( sourceDominant || target.getScope() == null )
            {
                target.setScope( src );
                target.setLocation( "scope", source.getLocation( "scope" ) );
            }
        }
    }
