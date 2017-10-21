	@Test
	public void synthesizeAnnotationWithImplicitAliasesForAliasPair() throws Exception {
		Class<?> clazz = ImplicitAliasesForAliasPairContextConfigClass.class;
		ImplicitAliasesForAliasPairContextConfig config = clazz.getAnnotation(ImplicitAliasesForAliasPairContextConfig.class);
		assertNotNull(config);

		ImplicitAliasesForAliasPairContextConfig synthesizedConfig = synthesizeAnnotation(config);
		assertThat(synthesizedConfig, instanceOf(SynthesizedAnnotation.class));

		assertEquals("xmlFile: ", "test.xml", synthesizedConfig.xmlFile());
		assertEquals("groovyScript: ", "test.xml", synthesizedConfig.groovyScript());
	}
