	@Test
	public void getAttributeAliasNamesFromComposedAnnotationWithImplicitAliasesForAliasPair() throws Exception {
		Method xmlFile = ImplicitAliasesForAliasPairContextConfig.class.getDeclaredMethod("xmlFile");
		Method groovyScript = ImplicitAliasesForAliasPairContextConfig.class.getDeclaredMethod("groovyScript");

		// Meta-annotation attribute overrides
		assertEquals("location", getAttributeOverrideName(xmlFile, ContextConfig.class));
		assertEquals("value", getAttributeOverrideName(groovyScript, ContextConfig.class));

		// Implicit aliases
		assertThat(getAttributeAliasNames(xmlFile), containsInAnyOrder("groovyScript"));
		assertThat(getAttributeAliasNames(groovyScript), containsInAnyOrder("xmlFile"));
	}
