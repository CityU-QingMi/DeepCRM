	@Test
	public void synthesizeAnnotationWithTransitiveImplicitAliasesForAliasPair() throws Exception {
		Class<?> clazz = TransitiveImplicitAliasesForAliasPairContextConfigClass.class;
		TransitiveImplicitAliasesForAliasPairContextConfig config = clazz.getAnnotation(TransitiveImplicitAliasesForAliasPairContextConfig.class);
		assertNotNull(config);

		TransitiveImplicitAliasesForAliasPairContextConfig synthesizedConfig = synthesizeAnnotation(config);
		assertThat(synthesizedConfig, instanceOf(SynthesizedAnnotation.class));

		assertEquals("xml: ", "test.xml", synthesizedConfig.xml());
		assertEquals("groovy: ", "test.xml", synthesizedConfig.groovy());
	}
