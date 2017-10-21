    @Override
    public Toolchain getToolchainFromBuildContext( String type, MavenSession session )
    {
        Map<String, Object> context = retrieveContext( session );

        ToolchainModel model = (ToolchainModel) context.get( getStorageKey( type ) );

        if ( model != null )
        {
            List<Toolchain> toolchains = selectToolchains( Collections.singletonList( model ), type, null );
            
            if ( !toolchains.isEmpty() )
            {
                return toolchains.get( 0 );
            }
        }

        return null;
    }
