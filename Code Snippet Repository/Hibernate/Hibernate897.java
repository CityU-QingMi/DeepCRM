	public ModelBinder(final MetadataBuildingContext context) {
		this.metadataBuildingContext = context;

		this.database = context.getMetadataCollector().getDatabase();
		this.objectNameNormalizer = new ObjectNameNormalizer() {
			@Override
			protected MetadataBuildingContext getBuildingContext() {
				return context;
			}
		};
		this.implicitNamingStrategy = context.getBuildingOptions().getImplicitNamingStrategy();
		this.relationalObjectBinder = new RelationalObjectBinder( context );
	}
