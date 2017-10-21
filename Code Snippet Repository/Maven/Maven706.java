    private void debugMojoExecution( MojoExecution mojoExecution )
    {
        String mojoExecId =
            mojoExecution.getGroupId() + ':' + mojoExecution.getArtifactId() + ':' + mojoExecution.getVersion() + ':'
                + mojoExecution.getGoal() + " (" + mojoExecution.getExecutionId() + ')';

        Map<String, List<MojoExecution>> forkedExecutions = mojoExecution.getForkedExecutions();
        if ( !forkedExecutions.isEmpty() )
        {
            for ( Map.Entry<String, List<MojoExecution>> fork : forkedExecutions.entrySet() )
            {
                logger.debug( "--- init fork of " + fork.getKey() + " for " + mojoExecId + " ---" );

                debugDependencyRequirements( fork.getValue() );

                for ( MojoExecution forkedExecution : fork.getValue() )
                {
                    debugMojoExecution( forkedExecution );
                }

                logger.debug( "--- exit fork of " + fork.getKey() + " for " + mojoExecId + " ---" );
            }
        }

        logger.debug( "-----------------------------------------------------------------------" );
        logger.debug( "Goal:          " + mojoExecId );
        logger.debug(
            "Style:         " + ( mojoExecution.getMojoDescriptor().isAggregator() ? "Aggregating" : "Regular" ) );
        logger.debug( "Configuration: " + mojoExecution.getConfiguration() );
    }
