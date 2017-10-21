	@Test
	public void synthesizeAnnotationWithAttributeAliasForMetaAnnotationThatIsNotMetaPresent() throws Exception {
		AliasedComposedContextConfigNotMetaPresent annotation =
				AliasedComposedContextConfigNotMetaPresentClass.class.getAnnotation(AliasedComposedContextConfigNotMetaPresent.class);
		exception.expect(AnnotationConfigurationException.class);
		exception.expectMessage(startsWith("@AliasFor declaration on attribute 'xmlConfigFile' in annotation"));
		exception.expectMessage(containsString(AliasedComposedContextConfigNotMetaPresent.class.getName()));
		exception.expectMessage(containsString("declares an alias for attribute 'location' in meta-annotation"));
		exception.expectMessage(containsString(ContextConfig.class.getName()));
		exception.expectMessage(containsString("not meta-present"));
		synthesizeAnnotation(annotation);
	}
