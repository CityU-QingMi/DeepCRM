    private MojoExecution newMojoExecution()
    {
        PluginDescriptor pd = new PluginDescriptor();
        pd.setArtifactId( "my-plugin" );
        pd.setGroupId( "org.myco.plugins" );
        pd.setVersion( "1" );

        MojoDescriptor md = new MojoDescriptor();
        md.setPluginDescriptor( pd );

        pd.addComponentDescriptor( md );

        return new MojoExecution( md );
    }
