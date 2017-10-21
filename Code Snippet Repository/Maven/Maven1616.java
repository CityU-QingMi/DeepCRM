    protected void mergeScm_DeveloperConnection( Scm target, Scm source, boolean sourceDominant,
                                                 Map<Object, Object> context )
    {
        String src = source.getDeveloperConnection();
        if ( src != null )
        {
            if ( sourceDominant || target.getDeveloperConnection() == null )
            {
                target.setDeveloperConnection( src );
                target.setLocation( "developerConnection", source.getLocation( "developerConnection" ) );
            }
        }
    }
