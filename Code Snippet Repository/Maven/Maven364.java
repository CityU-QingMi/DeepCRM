    public void setUp()
        throws Exception
    {
        ArtifactResolver resolver = lookup( ArtifactResolver.class, "classpath" );
        DefaultArtifactDescriptorReader pomReader = (DefaultArtifactDescriptorReader)lookup(ArtifactDescriptorReader.class);
        pomReader.setArtifactResolver( resolver );

        projectBuilder = lookup( ProjectBuilder.class, "classpath" );

        // the metadata source looks up the default impl, so we have to trick it
        getContainer().addComponent( projectBuilder, ProjectBuilder.class, "default" );

        repositorySystem = lookup( RepositorySystem.class );
    }
