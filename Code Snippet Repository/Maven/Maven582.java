    private Plugin findPlugin( Model model, String groupId, String artifactId )
    {
        Validate.notBlank( groupId, "groupId can neither be null, empty nor blank" );
        Validate.notBlank( artifactId, "artifactId can neither be null, empty nor blank" );

        if ( model != null )
        {
            Build build = model.getBuild();
            if ( build != null )
            {
                for ( Plugin plugin : build.getPlugins() )
                {
                    if ( groupId.equals( plugin.getGroupId() ) && artifactId.equals( plugin.getArtifactId() ) )
                    {
                        return plugin;
                    }
                }

                PluginManagement mgmt = build.getPluginManagement();
                if ( mgmt != null )
                {
                    for ( Plugin plugin : mgmt.getPlugins() )
                    {
                        if ( groupId.equals( plugin.getGroupId() ) && artifactId.equals( plugin.getArtifactId() ) )
                        {
                            return plugin;
                        }
                    }
                }
            }
        }

        return null;
    }
