    protected void mergeRepositoryPolicy_Enabled( RepositoryPolicy target, RepositoryPolicy source,
                                                  boolean sourceDominant, Map<Object, Object> context )
    {
        String src = source.getEnabled();
        if ( src != null )
        {
            if ( sourceDominant || target.getEnabled() == null )
            {
                target.setEnabled( src );
                target.setLocation( "enabled", source.getLocation( "enabled" ) );
            }
        }
    }
