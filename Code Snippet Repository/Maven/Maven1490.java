    @Override
    public boolean presentInConfig( Profile profile, ProfileActivationContext context, ModelProblemCollector problems )
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
        return true;
    }
