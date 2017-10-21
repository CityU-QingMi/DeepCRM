    private static MojoExecution createMojoExecution( String goal, String executionId, MojoDescriptor mojoDescriptor )
    {
        final Plugin plugin = mojoDescriptor.getPluginDescriptor().getPlugin();
        MojoExecution result = new MojoExecution( plugin, goal, executionId );
        result.setConfiguration( new Xpp3Dom( executionId + "-" + goal ) );
        result.setMojoDescriptor( mojoDescriptor );
        result.setLifecyclePhase( mojoDescriptor.getPhase() );

        return result;

    }
