    @Override
    public MavenExecutionRequest addProfile( Profile profile )
    {
        Validate.notNull( profile, "profile cannot be null" );

        for ( Profile p : getProfiles() )
        {
            if ( p.getId() != null && p.getId().equals( profile.getId() ) )
            {
                return this;
            }
        }

        getProfiles().add( profile );

        return this;
    }
