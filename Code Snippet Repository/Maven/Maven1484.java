    @Override
    public boolean isActive( Profile profile, ProfileActivationContext context, ModelProblemCollector problems )
    {
        Activation activation = profile.getActivation();

        if ( activation == null )
        {
            return false;
        }

        String jdk = activation.getJdk();

        if ( jdk == null )
        {
            return false;
        }

        String version = context.getSystemProperties().get( "java.version" );

        if ( version == null || version.length() <= 0 )
        {
            problems.add( new ModelProblemCollectorRequest( Severity.ERROR, Version.BASE )
                    .setMessage( "Failed to determine Java version for profile " + profile.getId() )
                    .setLocation( activation.getLocation( "jdk" ) ) );
            return false;
        }

        if ( jdk.startsWith( "!" ) )
        {
            return !version.startsWith( jdk.substring( 1 ) );
        }
        else if ( isRange( jdk ) )
        {
            return isInRange( version, getRange( jdk ) );
        }
        else
        {
            return version.startsWith( jdk );
        }
    }
