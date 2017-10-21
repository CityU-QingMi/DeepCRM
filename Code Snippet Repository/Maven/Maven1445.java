    @Override
    protected void mergeScm_DeveloperConnection( Scm target, Scm source, boolean sourceDominant,
                                                 Map<Object, Object> context )
    {
        String src = source.getDeveloperConnection();
        if ( src != null )
        {
            if ( sourceDominant )
            {
                target.setDeveloperConnection( src );
                target.setLocation( "developerConnection", source.getLocation( "developerConnection" ) );
            }
            else if ( target.getDeveloperConnection() == null )
            {
                target.setDeveloperConnection( extrapolateChildUrl( src, context ) );
                target.setLocation( "developerConnection", source.getLocation( "developerConnection" ) );
            }
        }
    }
