	@SuppressWarnings("")
	public HbmMetadataSourceProcessorImpl(
			Collection<Binding> xmlBindings,
			MetadataBuildingContext rootBuildingContext) {
		this.rootBuildingContext = rootBuildingContext;
		final EntityHierarchyBuilder hierarchyBuilder = new EntityHierarchyBuilder();

		this.mappingDocuments = new ArrayList<MappingDocument>();

		for ( Binding xmlBinding : xmlBindings ) {
			if ( !JaxbHbmHibernateMapping.class.isInstance( xmlBinding.getRoot() ) ) {
				continue;
			}

			final MappingDocument mappingDocument = new MappingDocument(
					(JaxbHbmHibernateMapping) xmlBinding.getRoot(),
					xmlBinding.getOrigin(),
					rootBuildingContext
			);
			mappingDocuments.add( mappingDocument );
			hierarchyBuilder.indexMappingDocument( mappingDocument );
		}

		entityHierarchies = hierarchyBuilder.buildHierarchies();
		modelBinder = ModelBinder.prepare( rootBuildingContext );
	}
