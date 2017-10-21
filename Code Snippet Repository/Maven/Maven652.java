    public static CoreExtensionEntry discoverFrom( ClassRealm loader )
    {
        Set<String> artifacts = new LinkedHashSet<>();
        Set<String> packages = new LinkedHashSet<>();

        try
        {
            Enumeration<URL> urls = loader.getResources( builder.getExtensionDescriptorLocation() );
            while ( urls.hasMoreElements() )
            {

                try ( InputStream is = urls.nextElement().openStream() )
                {
                    ExtensionDescriptor descriptor = builder.build( is );
                    artifacts.addAll( descriptor.getExportedArtifacts() );
                    packages.addAll( descriptor.getExportedPackages() );
                }
            }
        }
        catch ( IOException ignored )
        {
            // exports descriptors are entirely optional
        }

        return new CoreExtensionEntry( loader, artifacts, packages );
    }
