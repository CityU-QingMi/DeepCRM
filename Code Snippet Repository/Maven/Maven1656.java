    protected void mergeConfigurationContainer_Inherited( ConfigurationContainer target, ConfigurationContainer source,
                                                          boolean sourceDominant, Map<Object, Object> context )
    {
        String src = source.getInherited();
        if ( src != null )
        {
            if ( sourceDominant || target.getInherited() == null )
            {
                target.setInherited( src );
                target.setLocation( "inherited", source.getLocation( "inherited" ) );
            }
        }
    }
