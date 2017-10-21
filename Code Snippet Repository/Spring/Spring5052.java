	@Test
	public void getMergedAnnotationAttributesOnClassWithLocalAnnotationThatShadowsAnnotationFromSuperclass() {
		Class<?> element = DerivedTxConfig.class;
		String name = TX_NAME;
		AnnotationAttributes attributes = getMergedAnnotationAttributes(element, name);
		assertNotNull("Annotation attributes for @Transactional on DerivedTxConfig", attributes);
		assertEquals("value for DerivedTxConfig.", "DerivedTxConfig", attributes.getString("value"));
		// Verify contracts between utility methods:
		assertTrue(isAnnotated(element, name));
	}
