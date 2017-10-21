	@Override
	protected Object instantiateTestClass(JupiterEngineExecutionContext parentExecutionContext,
			ExtensionRegistry registry, ExtensionContext extensionContext) {

		// Extensions registered for nested classes and below are not to be used for instantiating outer classes
		Optional<ExtensionRegistry> childExtensionRegistryForOuterInstance = Optional.empty();
		Object outerInstance = parentExecutionContext.getTestInstanceProvider().getTestInstance(
			childExtensionRegistryForOuterInstance);
		Constructor<?> constructor = ReflectionUtils.getDeclaredConstructor(getTestClass());
		return executableInvoker.invoke(constructor, outerInstance, extensionContext, registry);
	}
