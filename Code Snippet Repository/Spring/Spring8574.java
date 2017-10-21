	@Test
	public void overriddenContextConfigurationValue() throws Exception {
		Class<?> declaringClass = OverriddenMetaValueConfigTestCase.class;
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

		// overridden attribute:
		AnnotationAttributes attributes = descriptor.getAnnotationAttributes();

		// NOTE: we would like to be able to override the 'value' attribute; however,
		// Spring currently does not allow overrides for the 'value' attribute.
		// See SPR-11393 for related discussions.
		assertArrayEquals(new String[] { "foo.xml" }, attributes.getStringArray("value"));
	}
