	@Override
	@SuppressWarnings("")
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		DeprecationLogger.DEPRECATION_LOGGER.deprecatedSequenceGenerator( getClass().getName() );

		identifierType = type;

		final ObjectNameNormalizer normalizer = (ObjectNameNormalizer) params.get( IDENTIFIER_NORMALIZER );
		logicalQualifiedSequenceName = QualifiedNameParser.INSTANCE.parse(
				ConfigurationHelper.getString( SEQUENCE, params, "hibernate_sequence" ),
				normalizer.normalizeIdentifierQuoting( params.getProperty( CATALOG ) ),
				normalizer.normalizeIdentifierQuoting( params.getProperty( SCHEMA ) )
		);

		if ( params.containsKey( PARAMETERS ) ) {
			LOG.warn(
					"Use of 'parameters' config setting is no longer supported; " +
							"to specify initial-value or increment use the " +
							"org.hibernate.id.enhanced.SequenceStyleGenerator generator instead."
			);
		}
	}
