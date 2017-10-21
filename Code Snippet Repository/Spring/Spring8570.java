	@Test
	public void findAnnotationDescriptorForClassWithLocalMetaAnnotationAndAnnotatedSuperclass() {
		AnnotationDescriptor<ContextConfiguration> descriptor = findAnnotationDescriptor(
			MetaAnnotatedAndSuperAnnotatedContextConfigClass.class, ContextConfiguration.class);

		assertNotNull("AnnotationDescriptor should not be null", descriptor);
		assertEquals("rootDeclaringClass", MetaAnnotatedAndSuperAnnotatedContextConfigClass.class, descriptor.getRootDeclaringClass());
		assertEquals("declaringClass", MetaConfig.class, descriptor.getDeclaringClass());
		assertEquals("annotationType", ContextConfiguration.class, descriptor.getAnnotationType());
		assertNotNull("composedAnnotation should not be null", descriptor.getComposedAnnotation());
		assertEquals("composedAnnotationType", MetaConfig.class, descriptor.getComposedAnnotationType());

		assertArrayEquals("configured classes", new Class[] { String.class },
			descriptor.getAnnotationAttributes().getClassArray("classes"));
	}
