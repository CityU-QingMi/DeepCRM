	@Test
	public void synthesizeAnnotationWithImplicitAliasesWithMissingDefaultValues() throws Exception {
		Class<?> clazz = ImplicitAliasesWithMissingDefaultValuesContextConfigClass.class;
		Class<ImplicitAliasesWithMissingDefaultValuesContextConfig> annotationType = ImplicitAliasesWithMissingDefaultValuesContextConfig.class;
		ImplicitAliasesWithMissingDefaultValuesContextConfig config = clazz.getAnnotation(annotationType);
		assertNotNull(config);

		exception.expect(AnnotationConfigurationException.class);
		exception.expectMessage(startsWith("Misconfigured aliases:"));
		exception.expectMessage(containsString("attribute 'location1' in annotation [" + annotationType.getName() + "]"));
		exception.expectMessage(containsString("attribute 'location2' in annotation [" + annotationType.getName() + "]"));
		exception.expectMessage(containsString("default values"));

		synthesizeAnnotation(config, clazz);
	}
