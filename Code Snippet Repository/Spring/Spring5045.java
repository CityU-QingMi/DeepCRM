	@Test
	public void getMergedAnnotationAttributesWithInvalidConventionBasedComposedAnnotation() {
		Class<?> element = InvalidConventionBasedComposedContextConfigClass.class;
		exception.expect(AnnotationConfigurationException.class);
		exception.expectMessage(either(containsString("attribute 'value' and its alias 'locations'")).or(
				containsString("attribute 'locations' and its alias 'value'")));
		exception.expectMessage(either(
				containsString("values of [{duplicateDeclaration}] and [{requiredLocationsDeclaration}]")).or(
				containsString("values of [{requiredLocationsDeclaration}] and [{duplicateDeclaration}]")));
		exception.expectMessage(containsString("but only one is permitted"));
		getMergedAnnotationAttributes(element, ContextConfig.class);
	}
