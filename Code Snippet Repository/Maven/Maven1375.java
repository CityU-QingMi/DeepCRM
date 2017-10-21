    @Override
    public DefaultModelBuildingRequest setActiveProfileIds( List<String> activeProfileIds )
    {
        if ( activeProfileIds != null )
        {
            this.activeProfileIds = new ArrayList<>( activeProfileIds );
        }
        else
        {
            this.activeProfileIds = null;
        }

        return this;
    }
