	@SuppressWarnings("")
	private void initialize(StandardServiceRegistryBuilder ssrb) {
		final Dialect dialect = BaseCoreFunctionalTestCase.getDialect();

		ssrb.applySetting( AvailableSettings.CACHE_REGION_FACTORY, CachingRegionFactory.class.getName() );
		ssrb.applySetting( AvailableSettings.USE_NEW_ID_GENERATOR_MAPPINGS, "true" );
		if ( createSchema() ) {
			ssrb.applySetting( AvailableSettings.HBM2DDL_AUTO, "create-drop" );
			final String secondSchemaName = createSecondSchema();
			if ( StringHelper.isNotEmpty( secondSchemaName ) ) {
				if ( !H2Dialect.class.isInstance( dialect ) ) {
					// while it may be true that only H2 supports creation of a second schema via
					// URL (no idea whether that is accurate), every db should support creation of schemas
					// via DDL which SchemaExport can create for us.  See how this is used and
					// whether that usage could not just leverage that capability
					throw new UnsupportedOperationException( "Only H2 dialect supports creation of second schema." );
				}
				Helper.createH2Schema( secondSchemaName, ssrb.getSettings() );
			}
		}
		ssrb.applySetting( AvailableSettings.DIALECT, dialect.getClass().getName() );
	}
