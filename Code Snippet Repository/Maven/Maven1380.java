    public DefaultModelBuildingResult setActiveExternalProfiles( List<Profile> activeProfiles )
    {
        if ( activeProfiles != null )
        {
            this.activeExternalProfiles = new ArrayList<>( activeProfiles );
        }
        else
        {
            this.activeExternalProfiles.clear();
        }

        return this;
    }
