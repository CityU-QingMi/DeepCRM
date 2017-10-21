    public DefaultModelBuildingResult setActivePomProfiles( String modelId, List<Profile> activeProfiles )
    {
        // Intentionally notNull because Super POM may not contain a modelId
        Validate.notNull( modelId, "modelId cannot null" );

        if ( activeProfiles != null )
        {
            this.activePomProfiles.put( modelId, new ArrayList<>( activeProfiles ) );
        }
        else
        {
            this.activePomProfiles.remove( modelId );
        }

        return this;
    }
