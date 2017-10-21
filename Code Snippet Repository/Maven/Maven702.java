    private PluginExecution findPluginExecution( String executionId, Collection<PluginExecution> executions )
    {
        if ( StringUtils.isNotEmpty( executionId ) )
        {
            for ( PluginExecution execution : executions )
            {
                if ( executionId.equals( execution.getId() ) )
                {
                    return execution;
                }
            }
        }

        return null;
    }
