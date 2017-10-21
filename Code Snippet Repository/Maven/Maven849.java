    private PluginPrefixResult resolveFromRepository( PluginPrefixRequest request, RequestTrace trace,
                                                      String pluginGroup,
                                                      org.eclipse.aether.metadata.Metadata metadata,
                                                      ArtifactRepository repository )
    {
        if ( metadata != null && metadata.getFile() != null && metadata.getFile().isFile() )
        {
            try
            {
                Map<String, ?> options = Collections.singletonMap( MetadataReader.IS_STRICT, Boolean.FALSE );

                Metadata pluginGroupMetadata = metadataReader.read( metadata.getFile(), options );

                List<org.apache.maven.artifact.repository.metadata.Plugin> plugins = pluginGroupMetadata.getPlugins();

                if ( plugins != null )
                {
                    for ( org.apache.maven.artifact.repository.metadata.Plugin plugin : plugins )
                    {
                        if ( request.getPrefix().equals( plugin.getPrefix() ) )
                        {
                            return new DefaultPluginPrefixResult( pluginGroup, plugin.getArtifactId(), repository );
                        }
                    }
                }
            }
            catch ( IOException e )
            {
                invalidMetadata( request.getRepositorySession(), trace, metadata, repository, e );
            }
        }

        return null;
    }
