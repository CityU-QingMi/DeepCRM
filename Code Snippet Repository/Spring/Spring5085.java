	@Test
	public void getAttributeAliasNamesFromComposedAnnotationWithTransitiveImplicitAliasesWithImpliedAliasNamesOmitted()
			throws Exception {

		Method xml = TransitiveImplicitAliasesWithImpliedAliasNamesOmittedContextConfig.class.getDeclaredMethod("xml");
		Method groovy = TransitiveImplicitAliasesWithImpliedAliasNamesOmittedContextConfig.class.getDeclaredMethod("groovy");

		// Meta-annotation attribute overrides
		assertEquals("location", getAttributeOverrideName(xml, ContextConfig.class));
		assertEquals("location", getAttributeOverrideName(groovy, ContextConfig.class));

		// Explicit meta-annotation attribute overrides
		assertEquals("xmlFile", getAttributeOverrideName(xml, ImplicitAliasesWithImpliedAliasNamesOmittedContextConfig.class));
		assertEquals("location", getAttributeOverrideName(groovy, ImplicitAliasesWithImpliedAliasNamesOmittedContextConfig.class));

		// Transitive implicit aliases
		assertThat(getAttributeAliasNames(groovy), containsInAnyOrder("xml"));
		assertThat(getAttributeAliasNames(xml), containsInAnyOrder("groovy"));
	}
