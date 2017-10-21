	@Test
	public void getMergedAnnotationAttributesFavorsInheritedComposedAnnotationsOverMoreLocallyDeclaredComposedAnnotations() {
		Class<?> element = SubSubClassWithInheritedComposedAnnotation.class;
		String name = TX_NAME;
		AnnotationAttributes attributes = getMergedAnnotationAttributes(element, name);
		assertNotNull("AnnotationAttributtes for @Transactional on SubSubClassWithInheritedComposedAnnotation.", attributes);
		// Verify contracts between utility methods:
		assertTrue(isAnnotated(element, name));
		assertFalse("readOnly flag for SubSubClassWithInheritedComposedAnnotation.", attributes.getBoolean("readOnly"));
	}
