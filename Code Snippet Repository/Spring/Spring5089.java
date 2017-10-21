	@Test
	public void synthesizeAnnotationWithAttributeAliasWithMirroredAliasForWrongAttribute() throws Exception {
		AliasForWithMirroredAliasForWrongAttribute annotation =
				AliasForWithMirroredAliasForWrongAttributeClass.class.getAnnotation(AliasForWithMirroredAliasForWrongAttribute.class);

		exception.expect(AnnotationConfigurationException.class);
		exception.expectMessage(startsWith("Attribute 'bar' in"));
		exception.expectMessage(containsString(AliasForWithMirroredAliasForWrongAttribute.class.getName()));
		exception.expectMessage(either(containsString("must be declared as an @AliasFor [foo], not [quux]")).
				or(containsString("is declared as an @AliasFor nonexistent attribute 'quux'")));
		synthesizeAnnotation(annotation);
	}
