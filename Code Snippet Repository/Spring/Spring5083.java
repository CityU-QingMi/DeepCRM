	@Test
	public void getAttributeAliasNamesFromComposedAnnotationWithTransitiveImplicitAliases() throws Exception {
		Method xml = TransitiveImplicitAliasesContextConfig.class.getDeclaredMethod("xml");
		Method groovy = TransitiveImplicitAliasesContextConfig.class.getDeclaredMethod("groovy");

		// Explicit meta-annotation attribute overrides
		assertEquals("xmlFile", getAttributeOverrideName(xml, ImplicitAliasesContextConfig.class));
		assertEquals("groovyScript", getAttributeOverrideName(groovy, ImplicitAliasesContextConfig.class));

		// Transitive meta-annotation attribute overrides
		assertEquals("location", getAttributeOverrideName(xml, ContextConfig.class));
		assertEquals("location", getAttributeOverrideName(groovy, ContextConfig.class));

		// Transitive implicit aliases
		assertThat(getAttributeAliasNames(xml), containsInAnyOrder("groovy"));
		assertThat(getAttributeAliasNames(groovy), containsInAnyOrder("xml"));
	}
