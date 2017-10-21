	protected AbstractEntitySourceImpl(MappingDocument sourceMappingDocument, JaxbHbmEntityBaseDefinition jaxbEntityMapping) {
		super( sourceMappingDocument );
		this.jaxbEntityMapping = jaxbEntityMapping;

		this.entityNamingSource = extractEntityNamingSource( sourceMappingDocument, jaxbEntityMapping );

		this.attributePathBase = new AttributePath();
		this.attributeRoleBase = new AttributeRole( entityNamingSource.getEntityName() );

		this.tuplizerClassMap = extractTuplizers( jaxbEntityMapping );

		this.filterSources = buildFilterSources();

		for ( JaxbHbmFetchProfileType jaxbFetchProfile : jaxbEntityMapping.getFetchProfile() ) {
			FetchProfileBinder.processFetchProfile(
					sourceMappingDocument,
					jaxbFetchProfile,
					entityNamingSource.getClassName() != null
							? entityNamingSource.getClassName()
							: entityNamingSource.getEntityName()
			);
		}

		this.toolingHintContext = Helper.collectToolingHints(
				sourceMappingDocument.getToolingHintContext(),
				jaxbEntityMapping
		);
	}
