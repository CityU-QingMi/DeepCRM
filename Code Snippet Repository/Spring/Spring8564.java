	@Test
	@SuppressWarnings("")
	public void findAnnotationDescriptorForTypesForClassWithMetaAnnotatedInterface() {
		Component rawAnnotation = AnnotationUtils.findAnnotation(ClassWithMetaAnnotatedInterface.class,
			Component.class);

		UntypedAnnotationDescriptor descriptor;

		descriptor = findAnnotationDescriptorForTypes(ClassWithMetaAnnotatedInterface.class, Service.class,
			Component.class, Order.class, Transactional.class);
		assertNotNull(descriptor);
		assertEquals(ClassWithMetaAnnotatedInterface.class, descriptor.getRootDeclaringClass());
		assertEquals(Meta1.class, descriptor.getDeclaringClass());
		assertEquals(rawAnnotation, descriptor.getAnnotation());
		assertEquals(Meta1.class, descriptor.getComposedAnnotation().annotationType());
	}
