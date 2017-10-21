    private void validateProjects( List<MavenProject> projects )
    {
        Map<String, MavenProject> projectsMap = new HashMap<>();

        for ( MavenProject p : projects )
        {
            String projectKey = ArtifactUtils.key( p.getGroupId(), p.getArtifactId(), p.getVersion() );

            projectsMap.put( projectKey, p );
        }

        for ( MavenProject project : projects )
        {
            // MNG-1911 / MNG-5572: Building plugins with extensions cannot be part of reactor
            for ( Plugin plugin : project.getBuildPlugins() )
            {
                if ( plugin.isExtensions() )
                {
                    String pluginKey = ArtifactUtils.key( plugin.getGroupId(), plugin.getArtifactId(),
                                                          plugin.getVersion() );

                    if ( projectsMap.containsKey( pluginKey ) )
                    {
                        logger.warn( project.getName() + " uses " + plugin.getKey()
                            + " as extensions, which is not possible within the same reactor build. "
                            + "This plugin was pulled from the local repository!" );
                    }
                }
            }
        }
    }
