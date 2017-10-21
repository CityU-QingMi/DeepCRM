	@Test
	public void findAnnotationDescriptorForClassWithMetaAnnotatedInterface() {
		Component rawAnnotation = AnnotationUtils.findAnnotation(ClassWithMetaAnnotatedInterface.class,
			Component.class);

		AnnotationDescriptor<Component> descriptor;

		descriptor = findAnnotationDescriptor(ClassWithMetaAnnotatedInterface.class, Component.class);
		assertNotNull(descriptor);
		assertEquals(ClassWithMetaAnnotatedInterface.class, descriptor.getRootDeclaringClass());
		assertEquals(Meta1.class, descriptor.getDeclaringClass());
		assertEquals(rawAnnotation, descriptor.getAnnotation());
		assertEquals(Meta1.class, descriptor.getComposedAnnotation().annotationType());
	}
