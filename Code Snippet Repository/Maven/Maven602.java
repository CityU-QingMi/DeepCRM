    @Override
    public MavenExecutionRequest addActiveProfile( String profile )
    {
        if ( !getActiveProfiles().contains( profile ) )
        {
            getActiveProfiles().add( profile );
        }

        return this;
    }
