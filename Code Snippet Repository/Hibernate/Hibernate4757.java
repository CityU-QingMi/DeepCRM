	@SuppressWarnings("")
	public void execute(EnumSet<TargetType> targetTypes, Metadata metadata, ServiceRegistry serviceRegistry) {
		if ( targetTypes.isEmpty() ) {
			LOG.debug( "Skipping SchemaExport as no targets were specified" );
			return;
		}

		exceptions.clear();
		LOG.runningHbm2ddlSchemaUpdate();

		Map config = new HashMap();
		config.putAll( serviceRegistry.getService( ConfigurationService.class ).getSettings() );
		config.put( AvailableSettings.HBM2DDL_DELIMITER, delimiter );
		config.put( AvailableSettings.FORMAT_SQL, format );

		final SchemaManagementTool tool = serviceRegistry.getService( SchemaManagementTool.class );

		final ExceptionHandler exceptionHandler = haltOnError
				? ExceptionHandlerHaltImpl.INSTANCE
				: new ExceptionHandlerCollectingImpl();

		final ExecutionOptions executionOptions = SchemaManagementToolCoordinator.buildExecutionOptions(
				config,
				exceptionHandler
		);

		final TargetDescriptor targetDescriptor = SchemaExport.buildTargetDescriptor( targetTypes, outputFile, serviceRegistry );

		try {
			tool.getSchemaMigrator( config ).doMigration( metadata, executionOptions, targetDescriptor );
		}
		finally {
			if ( exceptionHandler instanceof ExceptionHandlerCollectingImpl ) {
				exceptions.addAll( ( (ExceptionHandlerCollectingImpl) exceptionHandler ).getExceptions() );
			}
		}
	}
