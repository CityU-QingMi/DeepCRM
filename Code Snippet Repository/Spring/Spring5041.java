	@Test
	public void getMergedAnnotationAttributesOnClassWithLocalAnnotation() {
		Class<?> element = TxConfig.class;
		String name = TX_NAME;
		AnnotationAttributes attributes = getMergedAnnotationAttributes(element, name);
		assertNotNull("Annotation attributes for @Transactional on TxConfig", attributes);
		assertEquals("value for TxConfig.", "TxConfig", attributes.getString("value"));
		// Verify contracts between utility methods:
		assertTrue(isAnnotated(element, name));
	}
