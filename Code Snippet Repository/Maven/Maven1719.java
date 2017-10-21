    protected void mergeDependency_Optional( Dependency target, Dependency source, boolean sourceDominant,
                                             Map<Object, Object> context )
    {
        String src = source.getOptional();
        if ( src != null )
        {
            if ( sourceDominant || target.getOptional() == null )
            {
                target.setOptional( src );
                target.setLocation( "optional", source.getLocation( "optional" ) );
            }
        }
    }
