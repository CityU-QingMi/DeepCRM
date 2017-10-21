	@Override
	public JupiterEngineExecutionContext before(JupiterEngineExecutionContext context) throws Exception {
		ThrowableCollector throwableCollector = context.getThrowableCollector();

		invokeBeforeAllCallbacks(context);
		if (throwableCollector.isEmpty()) {
			context.beforeAllMethodsExecuted(true);
			invokeBeforeAllMethods(context);
		}

		throwableCollector.assertEmpty();

		return context;
	}
