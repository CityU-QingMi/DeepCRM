	@Test
	@SuppressWarnings("")
	public void findAnnotationDescriptorForTypesWithMetaAnnotationWithOverriddenAttributes() throws Exception {
		Class<?> startClass = MetaConfigWithOverriddenAttributesTestCase.class;
		Class<ContextConfiguration> annotationType = ContextConfiguration.class;

		UntypedAnnotationDescriptor descriptor = findAnnotationDescriptorForTypes(startClass, Service.class,
			ContextConfiguration.class, Order.class, Transactional.class);

		assertNotNull(descriptor);
		assertEquals(startClass, descriptor.getRootDeclaringClass());
		assertEquals(annotationType, descriptor.getAnnotationType());
		assertArrayEquals(new Class[] {}, ((ContextConfiguration) descriptor.getAnnotation()).value());
		assertArrayEquals(new Class[] { MetaAnnotationUtilsTests.class },
			descriptor.getAnnotationAttributes().getClassArray("classes"));
		assertNotNull(descriptor.getComposedAnnotation());
		assertEquals(MetaConfig.class, descriptor.getComposedAnnotationType());
	}
