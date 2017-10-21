	public InformationExtractorJdbcDatabaseMetaDataImpl(ExtractionContext extractionContext) {
		this.extractionContext = extractionContext;

		ConfigurationService configService = extractionContext.getServiceRegistry()
				.getService( ConfigurationService.class );

		final String extraPhysycalTableTypesConfig = configService.getSetting(
				AvailableSettings.EXTRA_PHYSICAL_TABLE_TYPES,
				StandardConverters.STRING,
				""
		);
		if ( !"".equals( extraPhysycalTableTypesConfig.trim() ) ) {
			this.extraPhysicalTableTypes = StringHelper.splitTrimmingTokens(
					",;",
					extraPhysycalTableTypesConfig,
					false
			);
		}

		final List<String> tableTypesList = new ArrayList<>();
		tableTypesList.add( "TABLE" );
		tableTypesList.add( "VIEW" );
		if ( ConfigurationHelper.getBoolean( AvailableSettings.ENABLE_SYNONYMS, configService.getSettings(), false ) ) {
			tableTypesList.add( "SYNONYM" );
		}
		if ( extraPhysicalTableTypes != null ) {
			Collections.addAll( tableTypesList, extraPhysicalTableTypes );
		}
		extractionContext.getJdbcEnvironment().getDialect().augmentRecognizedTableTypes( tableTypesList );

		this.tableTypes = tableTypesList.toArray( new String[ tableTypesList.size() ] );
	}
