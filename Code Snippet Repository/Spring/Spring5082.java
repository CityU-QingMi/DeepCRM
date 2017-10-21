	@Test
	public void getAttributeAliasNamesFromComposedAnnotationWithImplicitAliasesWithImpliedAliasNamesOmitted()
			throws Exception {

		Method value = ImplicitAliasesWithImpliedAliasNamesOmittedContextConfig.class.getDeclaredMethod("value");
		Method location = ImplicitAliasesWithImpliedAliasNamesOmittedContextConfig.class.getDeclaredMethod("location");
		Method xmlFile = ImplicitAliasesWithImpliedAliasNamesOmittedContextConfig.class.getDeclaredMethod("xmlFile");

		// Meta-annotation attribute overrides
		assertEquals("value", getAttributeOverrideName(value, ContextConfig.class));
		assertEquals("location", getAttributeOverrideName(location, ContextConfig.class));
		assertEquals("location", getAttributeOverrideName(xmlFile, ContextConfig.class));

		// Implicit aliases
		assertThat(getAttributeAliasNames(value), containsInAnyOrder("location", "xmlFile"));
		assertThat(getAttributeAliasNames(location), containsInAnyOrder("value", "xmlFile"));
		assertThat(getAttributeAliasNames(xmlFile), containsInAnyOrder("value", "location"));
	}
