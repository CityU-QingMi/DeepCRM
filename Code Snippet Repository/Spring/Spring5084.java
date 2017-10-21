	@Test
	public void getAttributeAliasNamesFromComposedAnnotationWithTransitiveImplicitAliasesForAliasPair() throws Exception {
		Method xml = TransitiveImplicitAliasesForAliasPairContextConfig.class.getDeclaredMethod("xml");
		Method groovy = TransitiveImplicitAliasesForAliasPairContextConfig.class.getDeclaredMethod("groovy");

		// Explicit meta-annotation attribute overrides
		assertEquals("xmlFile", getAttributeOverrideName(xml, ImplicitAliasesForAliasPairContextConfig.class));
		assertEquals("groovyScript", getAttributeOverrideName(groovy, ImplicitAliasesForAliasPairContextConfig.class));

		// Transitive implicit aliases
		assertThat(getAttributeAliasNames(xml), containsInAnyOrder("groovy"));
		assertThat(getAttributeAliasNames(groovy), containsInAnyOrder("xml"));
	}
