    public boolean isActive( Profile profile )
        throws ProfileActivationException
    {
        Activation activation = profile.getActivation();

        String jdk = activation.getJdk();

        // null case is covered by canDetermineActivation(), so we can do a straight startsWith() here.
        if ( jdk.startsWith( "[" ) || jdk.startsWith( "(" ) )
        {
            try
            {
                return matchJdkVersionRange( jdk );
            }
            catch ( InvalidVersionSpecificationException e )
            {
                throw new ProfileActivationException( "Invalid JDK version in profile '" + profile.getId() + "': "
                    + e.getMessage() );
            }
        }

        boolean reverse = false;

        if ( jdk.startsWith( "!" ) )
        {
            reverse = true;
            jdk = jdk.substring( 1 );
        }

        if ( getJdkVersion().startsWith( jdk ) )
        {
            return !reverse;
        }
        else
        {
            return reverse;
        }
    }
