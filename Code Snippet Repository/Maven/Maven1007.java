    private List<Toolchain> selectToolchains( List<ToolchainModel> models, String type,
                                              Map<String, String> requirements )
    {
        List<Toolchain> toolchains = new ArrayList<>();

        if ( models != null )
        {
            ToolchainFactory fact = factories.get( type );

            if ( fact == null )
            {
                logger.error( "Missing toolchain factory for type: " + type
                    + ". Possibly caused by misconfigured project." );
            }
            else
            {
                for ( ToolchainModel model : models )
                {
                    try
                    {
                        ToolchainPrivate toolchain = fact.createToolchain( model );
                        if ( requirements == null || toolchain.matchesRequirements( requirements ) )
                        {
                            toolchains.add( toolchain );
                        }
                    }
                    catch ( MisconfiguredToolchainException ex )
                    {
                        logger.error( "Misconfigured toolchain.", ex );
                    }
                }
            }
        }
        return toolchains;
    }
