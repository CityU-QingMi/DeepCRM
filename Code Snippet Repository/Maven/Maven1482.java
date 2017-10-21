    private boolean isActive( Profile profile, ProfileActivationContext context, ModelProblemCollector problems )
    {
        boolean isActive = false;
        for ( ProfileActivator activator : activators )
        {
            if ( activator.presentInConfig( profile, context, problems ) )
            {
                isActive = true;
            }
        }
        for ( ProfileActivator activator : activators )
        {
            try
            {
                if ( activator.presentInConfig( profile, context, problems ) )
                {
                    isActive &=  activator.isActive( profile, context, problems );
                }
            }
            catch ( RuntimeException e )
            {
                problems.add( new ModelProblemCollectorRequest( Severity.ERROR, Version.BASE )
                        .setMessage( "Failed to determine activation for profile " + profile.getId() )
                        .setLocation( profile.getLocation( "" ) )
                        .setException( e ) );
                return false;
            }
        }
        return isActive;
    }
