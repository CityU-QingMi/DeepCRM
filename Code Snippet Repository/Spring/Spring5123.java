	@Test
	public void getAnnotationAttributesWithoutAttributeAliases() {
		Component component = WebController.class.getAnnotation(Component.class);
		assertNotNull(component);

		AnnotationAttributes attributes = (AnnotationAttributes) getAnnotationAttributes(component);
		assertNotNull(attributes);
		assertEquals("value attribute: ", "webController", attributes.getString(VALUE));
		assertEquals(Component.class, attributes.annotationType());
	}
