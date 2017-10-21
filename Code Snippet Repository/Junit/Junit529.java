	@Test
	@ExtendWith(ExtensionContextParameterResolver.class)
	void extensionContextHierarchy(ExtensionContext methodExtensionContext) {
		assertThat(methodExtensionContext).isNotNull();
		assertThat(methodExtensionContext.getElement()).containsInstanceOf(Method.class);

		Optional<ExtensionContext> classExtensionContext = methodExtensionContext.getParent();
		assertThat(classExtensionContext).isNotEmpty();
		assertThat(classExtensionContext.orElse(null).getElement()).contains(ExtensionContextExecutionTests.class);

		Optional<ExtensionContext> engineExtensionContext = classExtensionContext.orElse(null).getParent();
		assertThat(engineExtensionContext).isNotEmpty();
		assertThat(engineExtensionContext.orElse(null).getElement()).isEmpty();

		assertThat(engineExtensionContext.orElse(null).getParent()).isEmpty();
	}
