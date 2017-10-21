    Map<String, Object> retrieveContext( MavenSession session )
    {
        Map<String, Object> context = null;

        if ( session != null )
        {
            PluginDescriptor desc = new PluginDescriptor();
            desc.setGroupId( PluginDescriptor.getDefaultPluginGroupId() );
            desc.setArtifactId( PluginDescriptor.getDefaultPluginArtifactId( "toolchains" ) );

            MavenProject current = session.getCurrentProject();

            if ( current != null )
            {
                //TODO why is this using the context
                context = session.getPluginContext( desc, current );
            }
        }

        return ( context != null ) ? context : new HashMap<String, Object>();
    }
