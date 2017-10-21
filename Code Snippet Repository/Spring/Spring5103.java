	@Test
	public void synthesizeAnnotationWithImplicitAliasesWithDuplicateValues() throws Exception {
		Class<?> clazz = ImplicitAliasesWithDuplicateValuesContextConfigClass.class;
		Class<ImplicitAliasesWithDuplicateValuesContextConfig> annotationType = ImplicitAliasesWithDuplicateValuesContextConfig.class;
		ImplicitAliasesWithDuplicateValuesContextConfig config = clazz.getAnnotation(annotationType);
		assertNotNull(config);

		ImplicitAliasesWithDuplicateValuesContextConfig synthesizedConfig = synthesizeAnnotation(config, clazz);
		assertNotNull(synthesizedConfig);

		exception.expect(AnnotationConfigurationException.class);
		exception.expectMessage(startsWith("In annotation"));
		exception.expectMessage(containsString(annotationType.getName()));
		exception.expectMessage(containsString("declared on class"));
		exception.expectMessage(containsString(clazz.getName()));
		exception.expectMessage(containsString("and synthesized from"));
		exception.expectMessage(either(containsString("attribute 'location1' and its alias 'location2'")).or(
				containsString("attribute 'location2' and its alias 'location1'")));
		exception.expectMessage(either(containsString("are present with values of [1] and [2]")).or(
				containsString("are present with values of [2] and [1]")));

		synthesizedConfig.location1();
	}
