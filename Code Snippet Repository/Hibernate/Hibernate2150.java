	private Format buildFormat(
			NameQualifierSupport nameQualifierSupport,
			String catalogSeparator,
			boolean catalogAtEnd) {
		switch ( nameQualifierSupport ) {
			case NONE: {
				return NoQualifierSupportFormat.INSTANCE;
			}
			case CATALOG: {
				return catalogAtEnd
						? new NameCatalogFormat( catalogSeparator )
						: new CatalogNameFormat( catalogSeparator );
			}
			case SCHEMA: {
				return SchemaNameFormat.INSTANCE;
			}
			default: {
				return catalogAtEnd
						? new SchemaNameCatalogFormat( catalogSeparator )
						: new CatalogSchemaNameFormat( catalogSeparator );
			}
		}
	}
