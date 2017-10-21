	@Test
	public void getMergedAnnotationAttributesFavorsLocalComposedAnnotationOverInheritedAnnotation() {
		Class<?> element = SubClassWithInheritedAnnotation.class;
		String name = TX_NAME;
		AnnotationAttributes attributes = getMergedAnnotationAttributes(element, name);
		assertNotNull("AnnotationAttributes for @Transactional on SubClassWithInheritedAnnotation", attributes);
		// Verify contracts between utility methods:
		assertTrue(isAnnotated(element, name));
		assertTrue("readOnly flag for SubClassWithInheritedAnnotation.", attributes.getBoolean("readOnly"));
	}
