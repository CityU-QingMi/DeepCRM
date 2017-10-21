	@Override
	public JupiterEngineExecutionContext prepare(JupiterEngineExecutionContext context) {
		Lifecycle lifecycle = getTestInstanceLifecycle(testClass, context.getConfigurationParameters());

		this.beforeAllMethods = findBeforeAllMethods(testClass, lifecycle == Lifecycle.PER_METHOD);
		this.afterAllMethods = findAfterAllMethods(testClass, lifecycle == Lifecycle.PER_METHOD);
		this.beforeEachMethods = findBeforeEachMethods(testClass);
		this.afterEachMethods = findAfterEachMethods(testClass);

		ExtensionRegistry registry = populateNewExtensionRegistryFromExtendWith(this.testClass,
			context.getExtensionRegistry());

		registerBeforeEachMethodAdapters(registry);
		registerAfterEachMethodAdapters(registry);

		ThrowableCollector throwableCollector = new ThrowableCollector();
		ClassExtensionContext extensionContext = new ClassExtensionContext(context.getExtensionContext(),
			context.getExecutionListener(), this, throwableCollector);

		// @formatter:off
		return context.extend()
				.withTestInstanceProvider(testInstanceProvider(context, registry, extensionContext, lifecycle))
				.withExtensionRegistry(registry)
				.withExtensionContext(extensionContext)
				.withThrowableCollector(throwableCollector)
				.build();
		// @formatter:on
	}
