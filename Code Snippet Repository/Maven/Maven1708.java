    protected void mergeRepositoryPolicy_UpdatePolicy( RepositoryPolicy target, RepositoryPolicy source,
                                                       boolean sourceDominant, Map<Object, Object> context )
    {
        String src = source.getUpdatePolicy();
        if ( src != null )
        {
            if ( sourceDominant || target.getUpdatePolicy() == null )
            {
                target.setUpdatePolicy( src );
                target.setLocation( "updatePolicy", source.getLocation( "updatePolicy" ) );
            }
        }
    }
