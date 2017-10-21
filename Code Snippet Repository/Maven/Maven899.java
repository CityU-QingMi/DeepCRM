    public ExtensionDescriptor build( File extensionJar )
        throws IOException
    {
        ExtensionDescriptor extensionDescriptor = null;

        if ( extensionJar.isFile() )
        {
            try ( JarFile pluginJar = new JarFile( extensionJar, false ) )
            {
                ZipEntry pluginDescriptorEntry = pluginJar.getEntry( getExtensionDescriptorLocation() );

                if ( pluginDescriptorEntry != null )
                {
                    try ( InputStream is = pluginJar.getInputStream( pluginDescriptorEntry ) )
                    {
                        extensionDescriptor = build( is );
                    }
                }
            }
        }
        else
        {
            File pluginXml = new File( extensionJar, getExtensionDescriptorLocation() );

            if ( pluginXml.canRead() )
            {
                try ( InputStream is = new BufferedInputStream( new FileInputStream( pluginXml ) ) )
                {
                    extensionDescriptor = build( is );
                }
            }
        }

        return extensionDescriptor;
    }
