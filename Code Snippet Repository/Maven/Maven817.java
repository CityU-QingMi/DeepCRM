    private PluginDescriptor parsePluginDescriptor( InputStream is, Plugin plugin, String descriptorLocation )
        throws PluginDescriptorParsingException
    {
        try
        {
            Reader reader = ReaderFactory.newXmlReader( is );

            PluginDescriptor pluginDescriptor = builder.build( reader, descriptorLocation );

            return pluginDescriptor;
        }
        catch ( IOException | PlexusConfigurationException e )
        {
            throw new PluginDescriptorParsingException( plugin, descriptorLocation, e );
        }
    }
