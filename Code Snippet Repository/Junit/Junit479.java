	@Test
	void fromClassTestDescriptor() {
		ClassTestDescriptor nestedClassDescriptor = nestedClassDescriptor();
		ClassTestDescriptor outerClassDescriptor = outerClassDescriptor(nestedClassDescriptor);

		ClassExtensionContext outerExtensionContext = new ClassExtensionContext(null, null, outerClassDescriptor, null);

		// @formatter:off
		assertAll("outerContext",
			() -> assertThat(outerExtensionContext.getElement()).contains(OuterClass.class),
			() -> assertThat(outerExtensionContext.getTestClass()).contains(OuterClass.class),
			() -> assertThat(outerExtensionContext.getTestInstance()).isEmpty(),
			() -> assertThat(outerExtensionContext.getTestMethod()).isEmpty(),
			() -> assertThat(outerExtensionContext.getRequiredTestClass()).isEqualTo(OuterClass.class),
			() -> assertThrows(PreconditionViolationException.class, () -> outerExtensionContext.getRequiredTestInstance()),
			() -> assertThrows(PreconditionViolationException.class, () -> outerExtensionContext.getRequiredTestMethod()),
			() -> assertThat(outerExtensionContext.getDisplayName()).isEqualTo(outerClassDescriptor.getDisplayName()),
			() -> assertThat(outerExtensionContext.getParent()).isEmpty()
		);
		// @formatter:on

		ClassExtensionContext nestedExtensionContext = new ClassExtensionContext(outerExtensionContext, null,
			nestedClassDescriptor, null);
		assertThat(nestedExtensionContext.getParent()).containsSame(outerExtensionContext);
	}
