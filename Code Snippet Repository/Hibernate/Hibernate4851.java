	public void doCreation(
			Metadata metadata,
			final ServiceRegistry serviceRegistry,
			final Map settings,
			final boolean manageNamespaces,
			GenerationTarget... targets) {
		doCreation(
				metadata,
				serviceRegistry.getService( JdbcEnvironment.class ).getDialect(),
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
