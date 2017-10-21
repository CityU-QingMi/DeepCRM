	private <T extends Extension> void invokeAllAfterMethodsOrCallbacks(JupiterEngineExecutionContext context,
			BiFunction<ExtensionContext, T, Executable> generator, Class<T> type) {

		ExtensionRegistry registry = context.getExtensionRegistry();
		ExtensionContext extensionContext = context.getExtensionContext();
		ThrowableCollector throwableCollector = context.getThrowableCollector();

		registry.getReversedExtensions(type).forEach(callback -> {
			Executable executable = generator.apply(extensionContext, callback);
			throwableCollector.execute(executable);
		});
	}
