    @Override
    public MavenExecutionRequest setActiveProfiles( List<String> activeProfiles )
    {
        if ( activeProfiles != null )
        {
            this.activeProfiles = new ArrayList<>( activeProfiles );
        }
        else
        {
            this.activeProfiles = null;
        }

        return this;
    }
