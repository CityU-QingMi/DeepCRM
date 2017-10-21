    public void addProfile( Profile profile )
    {
        String profileId = profile.getId();

        Profile existing = profilesById.get( profileId );
        if ( existing != null )
        {
            logger.warn( "Overriding profile: \'" + profileId + "\' (source: " + existing.getSource()
                + ") with new instance from source: " + profile.getSource() );
        }

        profilesById.put( profile.getId(), profile );

        Activation activation = profile.getActivation();

        if ( activation != null && activation.isActiveByDefault() )
        {
            activateAsDefault( profileId );
        }
    }
