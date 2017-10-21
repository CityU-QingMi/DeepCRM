    @Provides
    @Singleton
    Set<MetadataGeneratorFactory> provideMetadataGeneratorFactories( @Named( "snapshot" )
                                                                     MetadataGeneratorFactory snapshot,
                                                                     @Named( "versions" )
                                                                     MetadataGeneratorFactory versions )
    {
        Set<MetadataGeneratorFactory> factories = new HashSet<>();
        factories.add( snapshot );
        factories.add( versions );
        return Collections.unmodifiableSet( factories );
    }
