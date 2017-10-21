    @Override
    public boolean presentInConfig( Profile profile, ProfileActivationContext context, ModelProblemCollector problems )
    {
        Activation activation = profile.getActivation();

        if ( activation == null )
        {
            return false;
        }

        ActivationProperty property = activation.getProperty();

        if ( property == null )
        {
            return false;
        }
        return true;
    }
