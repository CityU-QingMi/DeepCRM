    @Override
    public DefaultModelBuildingRequest setProfiles( List<Profile> profiles )
    {
        if ( profiles != null )
        {
            this.profiles = new ArrayList<>( profiles );
        }
        else
        {
            this.profiles = null;
        }

        return this;
    }
