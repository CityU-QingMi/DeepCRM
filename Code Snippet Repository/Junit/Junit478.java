	@Test
	void fromJupiterEngineDescriptor() {
		JupiterEngineDescriptor engineTestDescriptor = new JupiterEngineDescriptor(
			UniqueId.root("engine", "junit-jupiter"));

		JupiterEngineExtensionContext engineContext = new JupiterEngineExtensionContext(null, engineTestDescriptor);

		// @formatter:off
		assertAll("engineContext",
			() -> assertThat(engineContext.getElement()).isEmpty(),
			() -> assertThat(engineContext.getTestClass()).isEmpty(),
			() -> assertThat(engineContext.getTestInstance()).isEmpty(),
			() -> assertThat(engineContext.getTestMethod()).isEmpty(),
			() -> assertThrows(PreconditionViolationException.class, () -> engineContext.getRequiredTestClass()),
			() -> assertThrows(PreconditionViolationException.class, () -> engineContext.getRequiredTestInstance()),
			() -> assertThrows(PreconditionViolationException.class, () -> engineContext.getRequiredTestMethod()),
			() -> assertThat(engineContext.getDisplayName()).isEqualTo(engineTestDescriptor.getDisplayName()),
			() -> assertThat(engineContext.getParent()).isEmpty(),
			() -> assertThat(engineContext.getRoot()).isSameAs(engineContext)
		);
		// @formatter:on
	}
