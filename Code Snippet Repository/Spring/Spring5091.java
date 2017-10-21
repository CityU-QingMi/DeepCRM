	@Test
	public void synthesizeAnnotationWithAttributeAliasForWithMissingDefaultValues() throws Exception {
		AliasForWithMissingDefaultValues annotation =
				AliasForWithMissingDefaultValuesClass.class.getAnnotation(AliasForWithMissingDefaultValues.class);
		exception.expect(AnnotationConfigurationException.class);
		exception.expectMessage(startsWith("Misconfigured aliases"));
		exception.expectMessage(containsString(AliasForWithMissingDefaultValues.class.getName()));
		exception.expectMessage(containsString("attribute 'foo' in annotation"));
		exception.expectMessage(containsString("attribute 'bar' in annotation"));
		exception.expectMessage(containsString("default values"));
		synthesizeAnnotation(annotation);
	}
