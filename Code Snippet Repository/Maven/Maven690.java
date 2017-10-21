    private void finalizeMojoConfiguration( MojoExecution mojoExecution )
    {
        MojoDescriptor mojoDescriptor = mojoExecution.getMojoDescriptor();

        Xpp3Dom executionConfiguration = mojoExecution.getConfiguration();
        if ( executionConfiguration == null )
        {
            executionConfiguration = new Xpp3Dom( "configuration" );
        }

        Xpp3Dom defaultConfiguration = getMojoConfiguration( mojoDescriptor );

        Xpp3Dom finalConfiguration = new Xpp3Dom( "configuration" );

        if ( mojoDescriptor.getParameters() != null )
        {
            for ( Parameter parameter : mojoDescriptor.getParameters() )
            {
                Xpp3Dom parameterConfiguration = executionConfiguration.getChild( parameter.getName() );

                if ( parameterConfiguration == null )
                {
                    parameterConfiguration = executionConfiguration.getChild( parameter.getAlias() );
                }

                Xpp3Dom parameterDefaults = defaultConfiguration.getChild( parameter.getName() );

                parameterConfiguration = Xpp3Dom.mergeXpp3Dom( parameterConfiguration, parameterDefaults,
                                                               Boolean.TRUE );

                if ( parameterConfiguration != null )
                {
                    parameterConfiguration = new Xpp3Dom( parameterConfiguration, parameter.getName() );

                    if ( StringUtils.isEmpty( parameterConfiguration.getAttribute( "implementation" ) )
                        && StringUtils.isNotEmpty( parameter.getImplementation() ) )
                    {
                        parameterConfiguration.setAttribute( "implementation", parameter.getImplementation() );
                    }

                    finalConfiguration.addChild( parameterConfiguration );
                }
            }
        }

        mojoExecution.setConfiguration( finalConfiguration );
    }
