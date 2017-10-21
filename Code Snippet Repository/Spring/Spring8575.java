	@Test
	public void contextConfigurationLocationsAndInheritLocations() throws Exception {
		Class<MetaLocationsConfigTestCase> declaringClass = MetaLocationsConfigTestCase.class;
		AnnotationDescriptor<ContextConfiguration> descriptor = findAnnotationDescriptor(declaringClass,
			ContextConfiguration.class);
		assertNotNull(descriptor);
		assertEquals(declaringClass, descriptor.getRootDeclaringClass());
		assertEquals(MetaLocationsConfig.class, descriptor.getComposedAnnotationType());
		assertEquals(ContextConfiguration.class, descriptor.getAnnotationType());
		assertNotNull(descriptor.getComposedAnnotation());
		assertEquals(MetaLocationsConfig.class, descriptor.getComposedAnnotationType());

		// direct access to annotation attributes:
		assertArrayEquals(new String[] { "foo.xml" }, descriptor.getAnnotation().locations());
		assertFalse(descriptor.getAnnotation().inheritLocations());
	}
