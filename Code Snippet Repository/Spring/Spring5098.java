	@Test
	public void synthesizeAnnotationWithTransitiveImplicitAliases() throws Exception {
		Class<?> clazz = TransitiveImplicitAliasesContextConfigClass.class;
		TransitiveImplicitAliasesContextConfig config = clazz.getAnnotation(TransitiveImplicitAliasesContextConfig.class);
		assertNotNull(config);

		TransitiveImplicitAliasesContextConfig synthesizedConfig = synthesizeAnnotation(config);
		assertThat(synthesizedConfig, instanceOf(SynthesizedAnnotation.class));

		assertEquals("xml: ", "test.xml", synthesizedConfig.xml());
		assertEquals("groovy: ", "test.xml", synthesizedConfig.groovy());
	}
