	@SuppressWarnings({"", ""})
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		returnClass = type.getReturnedClass();

		final JdbcEnvironment jdbcEnvironment = serviceRegistry.getService( JdbcEnvironment.class );

		qualifiedTableName = determineGeneratorTableName( params, jdbcEnvironment );

		segmentColumnName = determineSegmentColumnName( params, jdbcEnvironment );
		keySize = ConfigurationHelper.getInt( PK_LENGTH_NAME, params, DEFAULT_PK_LENGTH );
		segmentName = ConfigurationHelper.getString( PK_VALUE_NAME, params, params.getProperty( TABLE ) );

		valueColumnName = determineValueColumnName( params, jdbcEnvironment );

		//hilo config
		maxLo = ConfigurationHelper.getInt( MAX_LO, params, Short.MAX_VALUE );

		if ( maxLo >= 1 ) {
			hiloOptimizer = new LegacyHiLoAlgorithmOptimizer( returnClass, maxLo );
		}
	}
