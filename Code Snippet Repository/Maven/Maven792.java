    private static List<ComponentDescriptor<?>> clone( List<MojoDescriptor> mojos, PluginDescriptor pluginDescriptor )
    {
        List<ComponentDescriptor<?>> clones = null;

        if ( mojos != null )
        {
            clones = new ArrayList<>( mojos.size() );

            for ( MojoDescriptor mojo : mojos )
            {
                MojoDescriptor clone = mojo.clone();
                clone.setPluginDescriptor( pluginDescriptor );
                clones.add( clone );
            }
        }

        return clones;
    }
