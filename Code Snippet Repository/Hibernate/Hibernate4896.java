	public static ExecutionOptions buildExecutionOptions(
			final Map configurationValues,
			final ExceptionHandler exceptionHandler) {
		return new ExecutionOptions() {
			@Override
			public boolean shouldManageNamespaces() {
				return Helper.interpretNamespaceHandling( configurationValues );
			}

			@Override
			public Map getConfigurationValues() {
				return configurationValues;
			}

			@Override
			public ExceptionHandler getExceptionHandler() {
				return exceptionHandler;
			}
		};
	}
