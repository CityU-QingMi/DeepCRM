	@Test
	public void contextConfigurationValue() throws Exception {
		Class<MetaValueConfigTestCase> declaringClass = MetaValueConfigTestCase.class;
		AnnotationDescriptor<ContextConfiguration> descriptor = findAnnotationDescriptor(declaringClass,
			ContextConfiguration.class);
		assertNotNull(descriptor);
		assertEquals(declaringClass, descriptor.getRootDeclaringClass());
		assertEquals(MetaValueConfig.class, descriptor.getComposedAnnotationType());
		assertEquals(ContextConfiguration.class, descriptor.getAnnotationType());
		assertNotNull(descriptor.getComposedAnnotation());
		assertEquals(MetaValueConfig.class, descriptor.getComposedAnnotationType());

		// direct access to annotation value:
		assertArrayEquals(new String[] { "foo.xml" }, descriptor.getAnnotation().value());
	}
