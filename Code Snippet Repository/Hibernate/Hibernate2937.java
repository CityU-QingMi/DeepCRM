	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		final JdbcEnvironment jdbcEnvironment = serviceRegistry.getService( JdbcEnvironment.class );
		final Dialect dialect = jdbcEnvironment.getDialect();

		this.identifierType = type;
		boolean forceTableUse = ConfigurationHelper.getBoolean( FORCE_TBL_PARAM, params, false );

		final QualifiedName sequenceName = determineSequenceName( params, dialect, jdbcEnvironment );

		final int initialValue = determineInitialValue( params );
		int incrementSize = determineIncrementSize( params );

		final String optimizationStrategy = determineOptimizationStrategy( params, incrementSize );
		incrementSize = determineAdjustedIncrementSize( optimizationStrategy, incrementSize );

		if ( dialect.supportsSequences() && !forceTableUse ) {
			if ( !dialect.supportsPooledSequences() && OptimizerFactory.isPooledOptimizer( optimizationStrategy ) ) {
				forceTableUse = true;
				LOG.forcingTableUse();
			}
		}

		this.databaseStructure = buildDatabaseStructure(
				type,
				params,
				jdbcEnvironment,
				forceTableUse,
				sequenceName,
				initialValue,
				incrementSize
		);
		this.optimizer = OptimizerFactory.buildOptimizer(
				optimizationStrategy,
				identifierType.getReturnedClass(),
				incrementSize,
				ConfigurationHelper.getInt( INITIAL_PARAM, params, -1 )
		);
		this.databaseStructure.prepare( optimizer );
	}
