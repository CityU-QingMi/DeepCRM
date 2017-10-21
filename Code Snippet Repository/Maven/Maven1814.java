    @Override
    protected void configure()
    {
        install( new AetherModule() );
        bind( ArtifactDescriptorReader.class ) //
        .to( DefaultArtifactDescriptorReader.class ).in( Singleton.class );
        bind( VersionResolver.class ) //
        .to( DefaultVersionResolver.class ).in( Singleton.class );
        bind( VersionRangeResolver.class ) //
        .to( DefaultVersionRangeResolver.class ).in( Singleton.class );
        bind( MetadataGeneratorFactory.class ).annotatedWith( Names.named( "snapshot" ) ) //
        .to( SnapshotMetadataGeneratorFactory.class ).in( Singleton.class );
        bind( MetadataGeneratorFactory.class ).annotatedWith( Names.named( "versions" ) ) //
        .to( VersionsMetadataGeneratorFactory.class ).in( Singleton.class );
        bind( ModelBuilder.class ) //
        .toInstance( new DefaultModelBuilderFactory().newInstance() );
    }
