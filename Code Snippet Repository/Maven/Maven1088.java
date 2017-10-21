    public PluginVersionResult resolve( PluginVersionRequest request )
        throws PluginVersionResolutionException
    {
        return new PluginVersionResult()
        {
            public String getVersion()
            {
                return "0.42";
            }

            public ArtifactRepository getRepository()
            {
                return null;
            }
        };
    }
