	public EntityHierarchySourceImpl(RootEntitySourceImpl rootEntitySource) {
		this.rootEntitySource = rootEntitySource;
		this.rootEntitySource.injectHierarchy( this );

		this.identifierSource = interpretIdentifierSource( rootEntitySource );
		this.versionAttributeSource = interpretVersionSource( rootEntitySource );
		this.discriminatorSource = interpretDiscriminatorSource( rootEntitySource );
		this.multiTenancySource = interpretMultiTenancySource( rootEntitySource );

		this.caching = Helper.createCaching( entityElement().getCache() );
		this.naturalIdCaching = Helper.createNaturalIdCaching(
				rootEntitySource.jaxbEntityMapping().getNaturalIdCache()
		);

		collectedEntityNames.add( rootEntitySource.getEntityNamingSource().getEntityName() );
	}
