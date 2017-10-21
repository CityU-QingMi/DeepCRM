    public void validate( PluginDescriptor pluginDescriptor )
    {
/**/
/**/
/**/
/**/
        if ( !firstDescriptor )
        {
            return;
        }
        firstDescriptor = false;

        if ( !pluginArtifact.getGroupId().equals( pluginDescriptor.getGroupId() ) )
        {
            errors.add( "Plugin's descriptor contains the wrong group ID: " + pluginDescriptor.getGroupId() );
        }

        if ( !pluginArtifact.getArtifactId().equals( pluginDescriptor.getArtifactId() ) )
        {
            errors.add( "Plugin's descriptor contains the wrong artifact ID: " + pluginDescriptor.getArtifactId() );
        }

        if ( !pluginArtifact.getBaseVersion().equals( pluginDescriptor.getVersion() ) )
        {
            errors.add( "Plugin's descriptor contains the wrong version: " + pluginDescriptor.getVersion() );
        }
    }
