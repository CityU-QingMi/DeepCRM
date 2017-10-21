    public List getActiveProfiles()
        throws ProfileActivationException
    {
        DefaultProfileActivationContext context = new DefaultProfileActivationContext();
        context.setActiveProfileIds( activatedIds );
        context.setInactiveProfileIds( deactivatedIds );
        context.setSystemProperties( System.getProperties() );
        context.setUserProperties( requestProperties );

        final List<ProfileActivationException> errors = new ArrayList<>();

        List<Profile> profiles =
            profileSelector.getActiveProfiles( profilesById.values(), context, new ModelProblemCollector()
            {

                public void add( ModelProblemCollectorRequest req )
                {
                    if ( !ModelProblem.Severity.WARNING.equals( req.getSeverity() ) )
                    {
                        errors.add( new ProfileActivationException( req.getMessage(), req.getException() ) );
                    }
                }
            } );

        if ( !errors.isEmpty() )
        {
            throw errors.get( 0 );
        }

        return profiles;
    }
