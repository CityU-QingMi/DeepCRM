	@Test
	public void synthesizeAnnotationWithAttributeAliasForAttributeWithDifferentDefaultValue() throws Exception {
		AliasForAttributeWithDifferentDefaultValue annotation =
				AliasForAttributeWithDifferentDefaultValueClass.class.getAnnotation(AliasForAttributeWithDifferentDefaultValue.class);
		exception.expect(AnnotationConfigurationException.class);
		exception.expectMessage(startsWith("Misconfigured aliases"));
		exception.expectMessage(containsString(AliasForAttributeWithDifferentDefaultValue.class.getName()));
		exception.expectMessage(containsString("attribute 'foo' in annotation"));
		exception.expectMessage(containsString("attribute 'bar' in annotation"));
		exception.expectMessage(containsString("same default value"));
		synthesizeAnnotation(annotation);
	}
