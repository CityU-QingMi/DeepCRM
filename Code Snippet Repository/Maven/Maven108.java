    public void addPluginMapping( String goalPrefix,
                                  String artifactId,
                                  String name )
    {
        List<Plugin> plugins = getMetadata().getPlugins();
        boolean found = false;
        for ( Iterator<Plugin> i = plugins.iterator(); i.hasNext() && !found; )
        {
            Plugin plugin = i.next();
            if ( plugin.getPrefix().equals( goalPrefix ) )
            {
                found = true;
            }
        }
        if ( !found )
        {
            Plugin plugin = new Plugin();
            plugin.setPrefix( goalPrefix );
            plugin.setArtifactId( artifactId );
            plugin.setName( name );


            getMetadata().addPlugin( plugin );
        }
    }
