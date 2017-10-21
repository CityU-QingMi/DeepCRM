    public static MojoDescriptor createMojoDescriptor( String phaseName, boolean threadSafe )
    {
        final MojoDescriptor mojoDescriptor = new MojoDescriptor();
        mojoDescriptor.setPhase( phaseName );
        final PluginDescriptor descriptor = new PluginDescriptor();
        Plugin plugin = new Plugin();
        plugin.setArtifactId( "org.apache.maven.test.MavenExecutionPlan" );
        plugin.setGroupId( "stub-plugin-" + phaseName );
        descriptor.setPlugin( plugin );
        descriptor.setArtifactId( "artifact." + phaseName );
        mojoDescriptor.setPluginDescriptor( descriptor );
        mojoDescriptor.setThreadSafe( threadSafe );
        return mojoDescriptor;
    }
