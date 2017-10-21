    @Override
    public MavenExecutionRequest setInactiveProfiles( List<String> inactiveProfiles )
    {
        if ( inactiveProfiles != null )
        {
            this.inactiveProfiles = new ArrayList<>( inactiveProfiles );
        }
        else
        {
            this.inactiveProfiles = null;
        }

        return this;
    }
