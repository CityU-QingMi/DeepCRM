    public PluginPrefixResult resolve( PluginPrefixRequest request )
        throws NoPluginFoundForPrefixException
    {
        return new PluginPrefixResult()
        {
            public String getGroupId()
            {
                return "com.foobar";
            }

            public String getArtifactId()
            {
                return "bazbaz";
            }

            public ArtifactRepository getRepository()
            {
                return null;
            }
        };
    }
