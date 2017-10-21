    public ValidatingConfigurationListener( Object mojo, MojoDescriptor mojoDescriptor, ConfigurationListener delegate )
    {
        this.mojo = mojo;
        this.delegate = delegate;
        this.missingParameters = new HashMap<>();

        if ( mojoDescriptor.getParameters() != null )
        {
            for ( Parameter param : mojoDescriptor.getParameters() )
            {
                if ( param.isRequired() )
                {
                    missingParameters.put( param.getName(), param );
                }
            }
        }
    }
