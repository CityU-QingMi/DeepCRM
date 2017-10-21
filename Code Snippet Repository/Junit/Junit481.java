	@Test
	void fromMethodTestDescriptor() {
		TestMethodTestDescriptor methodTestDescriptor = methodDescriptor();
		ClassTestDescriptor classTestDescriptor = outerClassDescriptor(methodTestDescriptor);
		JupiterEngineDescriptor engineDescriptor = new JupiterEngineDescriptor(UniqueId.forEngine("junit-jupiter"));
		engineDescriptor.addChild(classTestDescriptor);

		Object testInstance = new OuterClass();
		Method testMethod = methodTestDescriptor.getTestMethod();

		JupiterEngineExtensionContext engineExtensionContext = new JupiterEngineExtensionContext(null,
			engineDescriptor);
		ClassExtensionContext classExtensionContext = new ClassExtensionContext(engineExtensionContext, null,
			classTestDescriptor, null);
		MethodExtensionContext methodExtensionContext = new MethodExtensionContext(classExtensionContext, null,
			methodTestDescriptor, testInstance, new ThrowableCollector());

		// @formatter:off
		assertAll("methodContext",
			() -> assertThat(methodExtensionContext.getElement()).contains(testMethod),
			() -> assertThat(methodExtensionContext.getTestClass()).contains(OuterClass.class),
			() -> assertThat(methodExtensionContext.getTestInstance()).contains(testInstance),
			() -> assertThat(methodExtensionContext.getTestMethod()).contains(testMethod),
			() -> assertThat(methodExtensionContext.getRequiredTestClass()).isEqualTo(OuterClass.class),
			() -> assertThat(methodExtensionContext.getRequiredTestInstance()).isEqualTo(testInstance),
			() -> assertThat(methodExtensionContext.getRequiredTestMethod()).isEqualTo(testMethod),
			() -> assertThat(methodExtensionContext.getDisplayName()).isEqualTo(methodTestDescriptor.getDisplayName()),
			() -> assertThat(methodExtensionContext.getParent()).contains(classExtensionContext),
			() -> assertThat(methodExtensionContext.getRoot()).isSameAs(engineExtensionContext)
		);
		// @formatter:on
	}
