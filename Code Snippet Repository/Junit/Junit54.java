	private void registerAfterEachMethodAdapters(ExtensionRegistry registry) {

		// Since the bottom-up ordering of afterEachMethods will later be reversed when the
		// synthesized AfterEachMethodAdapters are executed within MethodTestDescriptor, we
		// have to reverse the afterEachMethods list to put them in top-down order before we
		// register them as synthesized extensions.
		List<Method> reversed = new ArrayList<>(this.afterEachMethods);
		Collections.reverse(reversed);

		registerMethodsAsExtensions(reversed, registry, this::synthesizeAfterEachMethodAdapter);
	}
