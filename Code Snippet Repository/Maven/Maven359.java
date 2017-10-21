    private Plugin createPlugin( String groupId, String artifactId, String version, Map configuration )
    {
        Plugin plugin = new Plugin();
        plugin.setGroupId( groupId );
        plugin.setArtifactId( artifactId );
        plugin.setVersion( version );

        Xpp3Dom config = new Xpp3Dom( "configuration" );

        if( configuration != null )
        {
            for ( Object o : configuration.entrySet() )
            {
                Map.Entry entry = (Map.Entry) o;

                Xpp3Dom param = new Xpp3Dom( String.valueOf( entry.getKey() ) );
                param.setValue( String.valueOf( entry.getValue() ) );

                config.addChild( param );
            }
        }

        plugin.setConfiguration( config );

        return plugin;
    }
