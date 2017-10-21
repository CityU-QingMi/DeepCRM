    private MojoExecutionConfigurator mojoExecutionConfigurator( MojoExecution mojoExecution )
    {
        String configuratorId = mojoExecution.getMojoDescriptor().getComponentConfigurator();
        if ( configuratorId == null )
        {
            configuratorId = "default";
        }

        MojoExecutionConfigurator mojoExecutionConfigurator = mojoExecutionConfigurators.get( configuratorId );

        if ( mojoExecutionConfigurator == null )
        {
            //
            // The plugin has a custom component configurator but does not have a custom mojo execution configurator
            // so fall back to the default mojo execution configurator.
            //
            mojoExecutionConfigurator = mojoExecutionConfigurators.get( "default" );
        }
        return mojoExecutionConfigurator;
    }
