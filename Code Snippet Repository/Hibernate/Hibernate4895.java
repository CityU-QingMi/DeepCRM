	public static void process(
			final Metadata metadata,
			final ServiceRegistry serviceRegistry,
			final Map configurationValues,
			DelayedDropRegistry delayedDropRegistry) {
		final ActionGrouping actions = ActionGrouping.interpret( configurationValues );

		if ( actions.getDatabaseAction() == Action.NONE && actions.getScriptAction() == Action.NONE ) {
			// no actions specified
			log.debug( "No actions specified; doing nothing" );
			return;
		}

		final SchemaManagementTool tool = serviceRegistry.getService( SchemaManagementTool.class );
		final ConfigurationService configService = serviceRegistry.getService( ConfigurationService.class );

		boolean haltOnError = configService.getSetting( AvailableSettings.HBM2DDL_HALT_ON_ERROR, Boolean.class, false);

		final ExecutionOptions executionOptions = buildExecutionOptions(
				configurationValues,
				haltOnError ? ExceptionHandlerHaltImpl.INSTANCE :
						ExceptionHandlerLoggedImpl.INSTANCE
		);

		performScriptAction( actions.getScriptAction(), metadata, tool, serviceRegistry, executionOptions );
		performDatabaseAction( actions.getDatabaseAction(), metadata, tool, serviceRegistry, executionOptions );

		if ( actions.getDatabaseAction() == Action.CREATE_DROP ) {
			//noinspection unchecked
			delayedDropRegistry.registerOnCloseAction(
					tool.getSchemaDropper( configurationValues ).buildDelayedAction(
							metadata,
							executionOptions,
							buildDatabaseTargetDescriptor(
									configurationValues,
									DropSettingSelector.INSTANCE,
									serviceRegistry
							)
					)
			);
		}
	}
