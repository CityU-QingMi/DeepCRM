	public MappingDocument(
			JaxbHbmHibernateMapping documentRoot,
			Origin origin,
			MetadataBuildingContext rootBuildingContext) {
		this.documentRoot = documentRoot;
		this.origin = origin;
		this.rootBuildingContext = rootBuildingContext;

		// todo : allow for a split in default-lazy for singular/plural

		this.mappingDefaults = new OverriddenMappingDefaults.Builder( rootBuildingContext.getMappingDefaults() )
				.setImplicitSchemaName( documentRoot.getSchema() )
				.setImplicitCatalogName( documentRoot.getCatalog() )
				.setImplicitPackageName( documentRoot.getPackage() )
				.setImplicitPropertyAccessorName( documentRoot.getDefaultAccess() )
				.setImplicitCascadeStyleName( documentRoot.getDefaultCascade() )
				.setEntitiesImplicitlyLazy( documentRoot.isDefaultLazy() )
				.setAutoImportEnabled( documentRoot.isAutoImport() )
				.setPluralAttributesImplicitlyLazy( documentRoot.isDefaultLazy() )
				.build();

		this.toolingHintContext = Helper.collectToolingHints( null, documentRoot );
	}
