    public Set<Plugin> getNonThreadSafePlugins()
    {
        Set<Plugin> plugins = new HashSet<>();
        for ( ExecutionPlanItem executionPlanItem : planItem )
        {
            final MojoExecution mojoExecution = executionPlanItem.getMojoExecution();
            if ( !mojoExecution.getMojoDescriptor().isThreadSafe() )
            {
                plugins.add( mojoExecution.getPlugin() );
            }
        }
        return plugins;
    }
