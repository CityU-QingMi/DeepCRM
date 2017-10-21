	@Test
	public void getAnnotationAttributesWithAttributeAliases() throws Exception {
		Method method = WebController.class.getMethod("handleMappedWithValueAttribute");
		WebMapping webMapping = method.getAnnotation(WebMapping.class);
		AnnotationAttributes attributes = (AnnotationAttributes) getAnnotationAttributes(webMapping);
		assertNotNull(attributes);
		assertEquals(WebMapping.class, attributes.annotationType());
		assertEquals("name attribute: ", "foo", attributes.getString("name"));
		assertArrayEquals("value attribute: ", asArray("/test"), attributes.getStringArray(VALUE));
		assertArrayEquals("path attribute: ", asArray("/test"), attributes.getStringArray("path"));

		method = WebController.class.getMethod("handleMappedWithPathAttribute");
		webMapping = method.getAnnotation(WebMapping.class);
		attributes = (AnnotationAttributes) getAnnotationAttributes(webMapping);
		assertNotNull(attributes);
		assertEquals(WebMapping.class, attributes.annotationType());
		assertEquals("name attribute: ", "bar", attributes.getString("name"));
		assertArrayEquals("value attribute: ", asArray("/test"), attributes.getStringArray(VALUE));
		assertArrayEquals("path attribute: ", asArray("/test"), attributes.getStringArray("path"));
	}
