    public boolean isActive( Profile profile )
    {
        Activation activation = profile.getActivation();
        ActivationOS os = activation.getOs();

        boolean result = ensureAtLeastOneNonNull( os );

        if ( result && os.getFamily() != null )
        {
            result = determineFamilyMatch( os.getFamily() );
        }
        if ( result && os.getName() != null )
        {
            result = determineNameMatch( os.getName() );
        }
        if ( result && os.getArch() != null )
        {
            result = determineArchMatch( os.getArch() );
        }
        if ( result && os.getVersion() != null )
        {
            result = determineVersionMatch( os.getVersion() );
        }
        return result;
    }
