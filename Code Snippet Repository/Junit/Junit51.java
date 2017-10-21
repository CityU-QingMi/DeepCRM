	private TestInstanceProvider testInstanceProvider(JupiterEngineExecutionContext parentExecutionContext,
			ExtensionRegistry registry, ClassExtensionContext extensionContext, Lifecycle lifecycle) {

		if (lifecycle == Lifecycle.PER_CLASS) {
			// Eagerly load test instance for BeforeAllCallbacks, if necessary,
			// and store the instance in the ExtensionContext.
			Object instance = instantiateAndPostProcessTestInstance(parentExecutionContext, extensionContext, registry);
			extensionContext.setTestInstance(instance);
			return childRegistry -> instance;
		}

		// else Lifecycle.PER_METHOD
		return childRegistry -> instantiateAndPostProcessTestInstance(parentExecutionContext, extensionContext,
			childRegistry.orElse(registry));
	}
