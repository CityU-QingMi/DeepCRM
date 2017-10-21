    @Override
    public boolean isActive( Profile profile, ProfileActivationContext context, ModelProblemCollector problems )
    {
        Activation activation = profile.getActivation();

        if ( activation == null )
        {
            return false;
        }

        ActivationOS os = activation.getOs();

        if ( os == null )
        {
            return false;
        }

        boolean active = ensureAtLeastOneNonNull( os );

        if ( active && os.getFamily() != null )
        {
            active = determineFamilyMatch( os.getFamily() );
        }
        if ( active && os.getName() != null )
        {
            active = determineNameMatch( os.getName() );
        }
        if ( active && os.getArch() != null )
        {
            active = determineArchMatch( os.getArch() );
        }
        if ( active && os.getVersion() != null )
        {
            active = determineVersionMatch( os.getVersion() );
        }

        return active;
    }
