    @Override
    public MavenExecutionRequest addActiveProfiles( List<String> profiles )
    {
        for ( String profile : profiles )
        {
            addActiveProfile( profile );
        }

        return this;
    }
