	@Test
	public void getAttributeAliasNamesFromComposedAnnotationWithImplicitAliases() throws Exception {
		Method xmlFile = ImplicitAliasesContextConfig.class.getDeclaredMethod("xmlFile");
		Method groovyScript = ImplicitAliasesContextConfig.class.getDeclaredMethod("groovyScript");
		Method value = ImplicitAliasesContextConfig.class.getDeclaredMethod("value");
		Method location1 = ImplicitAliasesContextConfig.class.getDeclaredMethod("location1");
		Method location2 = ImplicitAliasesContextConfig.class.getDeclaredMethod("location2");
		Method location3 = ImplicitAliasesContextConfig.class.getDeclaredMethod("location3");

		// Meta-annotation attribute overrides
		assertEquals("location", getAttributeOverrideName(xmlFile, ContextConfig.class));
		assertEquals("location", getAttributeOverrideName(groovyScript, ContextConfig.class));
		assertEquals("location", getAttributeOverrideName(value, ContextConfig.class));

		// Implicit aliases
		assertThat(getAttributeAliasNames(xmlFile), containsInAnyOrder("value", "groovyScript", "location1", "location2", "location3"));
		assertThat(getAttributeAliasNames(groovyScript), containsInAnyOrder("value", "xmlFile", "location1", "location2", "location3"));
		assertThat(getAttributeAliasNames(value), containsInAnyOrder("xmlFile", "groovyScript", "location1", "location2", "location3"));
		assertThat(getAttributeAliasNames(location1), containsInAnyOrder("xmlFile", "groovyScript", "value", "location2", "location3"));
		assertThat(getAttributeAliasNames(location2), containsInAnyOrder("xmlFile", "groovyScript", "value", "location1", "location3"));
		assertThat(getAttributeAliasNames(location3), containsInAnyOrder("xmlFile", "groovyScript", "value", "location1", "location2"));
	}
