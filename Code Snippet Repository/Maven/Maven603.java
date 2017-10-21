    @Override
    public MavenExecutionRequest addInactiveProfile( String profile )
    {
        if ( !getInactiveProfiles().contains( profile ) )
        {
            getInactiveProfiles().add( profile );
        }

        return this;
    }
