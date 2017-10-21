    public static DefaultServiceLocator newServiceLocator()
    {
        DefaultServiceLocator locator = new DefaultServiceLocator();
        locator.addService( ArtifactDescriptorReader.class, DefaultArtifactDescriptorReader.class );
        locator.addService( VersionResolver.class, DefaultVersionResolver.class );
        locator.addService( VersionRangeResolver.class, DefaultVersionRangeResolver.class );
        locator.addService( MetadataGeneratorFactory.class, SnapshotMetadataGeneratorFactory.class );
        locator.addService( MetadataGeneratorFactory.class, VersionsMetadataGeneratorFactory.class );
        return locator;
    }
