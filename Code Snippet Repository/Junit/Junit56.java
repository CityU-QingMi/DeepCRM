	@Override
	public JupiterEngineExecutionContext prepare(JupiterEngineExecutionContext context) throws Exception {
		ExtensionRegistry extensionRegistry = createRegistryWithDefaultExtensions(context.getConfigurationParameters());
		EngineExecutionListener executionListener = context.getExecutionListener();
		ExtensionContext extensionContext = new JupiterEngineExtensionContext(executionListener, this);

		// @formatter:off
		return context.extend()
				.withExtensionRegistry(extensionRegistry)
				.withExtensionContext(extensionContext)
				.build();
		// @formatter:on
	}
