	private void assertAnnotationSynthesisWithImplicitAliases(Class<?> clazz, String expected) throws Exception {
		ImplicitAliasesContextConfig config = clazz.getAnnotation(ImplicitAliasesContextConfig.class);
		assertNotNull(config);

		ImplicitAliasesContextConfig synthesizedConfig = synthesizeAnnotation(config);
		assertThat(synthesizedConfig, instanceOf(SynthesizedAnnotation.class));

		assertEquals("value: ", expected, synthesizedConfig.value());
		assertEquals("location1: ", expected, synthesizedConfig.location1());
		assertEquals("xmlFile: ", expected, synthesizedConfig.xmlFile());
		assertEquals("groovyScript: ", expected, synthesizedConfig.groovyScript());
	}
