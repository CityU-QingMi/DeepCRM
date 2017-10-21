	@SuppressWarnings("")
	public void validate(Metadata metadata, ServiceRegistry serviceRegistry) {
		LOG.runningSchemaValidator();

		Map config = new HashMap();
		config.putAll( serviceRegistry.getService( ConfigurationService.class ).getSettings() );

		final SchemaManagementTool tool = serviceRegistry.getService( SchemaManagementTool.class );

		final ExecutionOptions executionOptions = SchemaManagementToolCoordinator.buildExecutionOptions(
				config,
				ExceptionHandlerHaltImpl.INSTANCE
		);

		tool.getSchemaValidator( config ).doValidation( metadata, executionOptions );
	}
