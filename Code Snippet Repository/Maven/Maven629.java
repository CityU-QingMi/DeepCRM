    @Override
    public MavenExecutionRequest populateFromToolchains( MavenExecutionRequest request, PersistedToolchains toolchains )
        throws MavenExecutionRequestPopulationException
    {
        if ( toolchains != null )
        {
            Map<String, List<ToolchainModel>> groupedToolchains = new HashMap<>( 2 );

            for ( ToolchainModel model : toolchains.getToolchains() )
            {
                if ( !groupedToolchains.containsKey( model.getType() ) )
                {
                    groupedToolchains.put( model.getType(), new ArrayList<ToolchainModel>() );
                }

                groupedToolchains.get( model.getType() ).add( model );
            }

            request.setToolchains( groupedToolchains );
        }
        return request;
    }
