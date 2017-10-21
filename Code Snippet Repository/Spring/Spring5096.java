	private void assertAnnotationSynthesisWithImplicitAliasesWithImpliedAliasNamesOmitted(Class<?> clazz,
			String expected) throws Exception {

		ImplicitAliasesWithImpliedAliasNamesOmittedContextConfig config = clazz.getAnnotation(
			ImplicitAliasesWithImpliedAliasNamesOmittedContextConfig.class);
		assertNotNull(config);

		ImplicitAliasesWithImpliedAliasNamesOmittedContextConfig synthesizedConfig = synthesizeAnnotation(config);
		assertThat(synthesizedConfig, instanceOf(SynthesizedAnnotation.class));

		assertEquals("value: ", expected, synthesizedConfig.value());
		assertEquals("locations: ", expected, synthesizedConfig.location());
		assertEquals("xmlFiles: ", expected, synthesizedConfig.xmlFile());
	}
