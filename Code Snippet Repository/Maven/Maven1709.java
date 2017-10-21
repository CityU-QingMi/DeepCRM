    protected void mergeRepositoryPolicy_ChecksumPolicy( RepositoryPolicy target, RepositoryPolicy source,
                                                         boolean sourceDominant, Map<Object, Object> context )
    {
        String src = source.getChecksumPolicy();
        if ( src != null )
        {
            if ( sourceDominant || target.getChecksumPolicy() == null )
            {
                target.setChecksumPolicy( src );
                target.setLocation( "checksumPolicy", source.getLocation( "checksumPolicy" ) );
            }
        }
    }
