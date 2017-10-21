    @Override
    public MavenExecutionRequest addInactiveProfiles( List<String> profiles )
    {
        for ( String profile : profiles )
        {
            addInactiveProfile( profile );
        }

        return this;
    }
