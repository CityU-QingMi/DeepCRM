	protected void addColumnBinding(SimpleValue value) {
		final String logicalColumnName;
		if ( StringHelper.isNotEmpty( this.logicalColumnName ) ) {
			logicalColumnName = this.logicalColumnName;
		}
		else {
			final ObjectNameNormalizer normalizer = context.getObjectNameNormalizer();
			final Database database = context.getMetadataCollector().getDatabase();
			final ImplicitNamingStrategy implicitNamingStrategy = context.getBuildingOptions()
					.getImplicitNamingStrategy();

			final Identifier implicitName = normalizer.normalizeIdentifierQuoting(
					implicitNamingStrategy.determineBasicColumnName(
							new ImplicitBasicColumnNameSource() {
								@Override
								public AttributePath getAttributePath() {
									return AttributePath.parse( propertyName );
								}

								@Override
								public boolean isCollectionElement() {
									return false;
								}

								@Override
								public MetadataBuildingContext getBuildingContext() {
									return context;
								}
							}
					)
			);
			logicalColumnName = implicitName.render( database.getDialect() );
		}
		context.getMetadataCollector().addColumnNameBinding( value.getTable(), logicalColumnName, getMappingColumn() );
	}
