	@SuppressWarnings("")
	public void execute(EnumSet<TargetType> targetTypes, Action action, Metadata metadata, ServiceRegistry serviceRegistry) {
		if ( action == Action.NONE ) {
			LOG.debug( "Skipping SchemaExport as Action.NONE was passed" );
			return;
		}

		if ( targetTypes.isEmpty() ) {
			LOG.debug( "Skipping SchemaExport as no targets were specified" );
			return;
		}

		exceptions.clear();

		LOG.runningHbm2ddlSchemaExport();

		final TargetDescriptor targetDescriptor = buildTargetDescriptor( targetTypes, outputFile, serviceRegistry );

		doExecution( action, needsJdbcConnection( targetTypes ), metadata, serviceRegistry, targetDescriptor );
	}
