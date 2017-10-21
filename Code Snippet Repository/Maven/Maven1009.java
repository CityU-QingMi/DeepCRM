    @Override
    public ToolchainPrivate[] getToolchainsForType( String type, MavenSession context )
        throws MisconfiguredToolchainException
    {
        List<ToolchainPrivate> toRet = new ArrayList<>();

        ToolchainFactory fact = factories.get( type );
        if ( fact == null )
        {
            logger.error( "Missing toolchain factory for type: " + type
                + ". Possibly caused by misconfigured project." );
        }
        else
        {
            List<ToolchainModel> availableToolchains = context.getRequest().getToolchains().get( type );

            if ( availableToolchains != null )
            {
                for ( ToolchainModel toolchainModel : availableToolchains )
                {
                    toRet.add( fact.createToolchain( toolchainModel ) );
                }
            }
            
            // add default toolchain
            ToolchainPrivate tool = fact.createDefaultToolchain();
            if ( tool != null )
            {
                toRet.add( tool );
            }
        }

        return toRet.toArray( new ToolchainPrivate[toRet.size()] );
    }
