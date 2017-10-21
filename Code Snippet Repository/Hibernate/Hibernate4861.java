	public void doDrop(
			Metadata metadata,
			final ServiceRegistry serviceRegistry,
			final Map settings,
			final boolean manageNamespaces,
			GenerationTarget... targets) {
		if ( targets == null || targets.length == 0 ) {
			final JdbcContext jdbcContext = tool.resolveJdbcContext( settings );
			targets = new GenerationTarget[] {
				new GenerationTargetToDatabase(
						serviceRegistry.getService( TransactionCoordinatorBuilder.class ).buildDdlTransactionIsolator( jdbcContext ),
						true
				)
			};
		}

		doDrop(
				metadata,
				new ExecutionOptions() {
					@Override
					public boolean shouldManageNamespaces() {
						return manageNamespaces;
					}

					@Override
					public Map getConfigurationValues() {
						return settings;
					}

					@Override
					public ExceptionHandler getExceptionHandler() {
						return ExceptionHandlerLoggedImpl.INSTANCE;
					}
				},
				serviceRegistry.getService( JdbcEnvironment.class ).getDialect(),
				new SourceDescriptor() {
					@Override
					public SourceType getSourceType() {
						return SourceType.METADATA;
					}

					@Override
					public ScriptSourceInput getScriptSourceInput() {
						return null;
					}
				},
				targets
		);
	}
