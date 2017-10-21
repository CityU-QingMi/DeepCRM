	@Test
	public void synthesizeAnnotationWithAttributeAliasForAttributeOfDifferentType() throws Exception {
		AliasForAttributeOfDifferentType annotation =
				AliasForAttributeOfDifferentTypeClass.class.getAnnotation(AliasForAttributeOfDifferentType.class);
		exception.expect(AnnotationConfigurationException.class);
		exception.expectMessage(startsWith("Misconfigured aliases"));
		exception.expectMessage(containsString(AliasForAttributeOfDifferentType.class.getName()));
		exception.expectMessage(containsString("attribute 'foo'"));
		exception.expectMessage(containsString("attribute 'bar'"));
		exception.expectMessage(containsString("same return type"));
		synthesizeAnnotation(annotation);
	}
