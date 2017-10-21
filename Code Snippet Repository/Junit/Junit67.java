	private <T extends Extension> void invokeBeforeMethodsOrCallbacksUntilExceptionOccurs(
			JupiterEngineExecutionContext context, BiFunction<ExtensionContext, T, Executable> generator,
			Class<T> type) {

		ExtensionRegistry registry = context.getExtensionRegistry();
		ExtensionContext extensionContext = context.getExtensionContext();
		ThrowableCollector throwableCollector = context.getThrowableCollector();

		for (T callback : registry.getExtensions(type)) {
			Executable executable = generator.apply(extensionContext, callback);
			throwableCollector.execute(executable);
			if (throwableCollector.isNotEmpty()) {
				break;
			}
		}
	}
