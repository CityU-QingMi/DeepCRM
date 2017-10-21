	@Test
	public void getMergedAnnotationAttributesFavorsInheritedAnnotationsOverMoreLocallyDeclaredComposedAnnotations() {
		Class<?> element = SubSubClassWithInheritedAnnotation.class;
		String name = TX_NAME;
		AnnotationAttributes attributes = getMergedAnnotationAttributes(element, name);
		assertNotNull("AnnotationAttributes for @Transactional on SubSubClassWithInheritedAnnotation", attributes);
		// Verify contracts between utility methods:
		assertTrue(isAnnotated(element, name));
		assertFalse("readOnly flag for SubSubClassWithInheritedAnnotation.", attributes.getBoolean("readOnly"));
	}
