	@Test
	@SuppressWarnings("")
	public void findAnnotationDescriptorForTypesWithInheritedAnnotationOnInterface() throws Exception {
		// Note: @Transactional is inherited
		Transactional rawAnnotation = InheritedAnnotationInterface.class.getAnnotation(Transactional.class);

		UntypedAnnotationDescriptor descriptor;

		descriptor = findAnnotationDescriptorForTypes(InheritedAnnotationInterface.class, Transactional.class);
		assertNotNull(descriptor);
		assertEquals(InheritedAnnotationInterface.class, descriptor.getRootDeclaringClass());
		assertEquals(InheritedAnnotationInterface.class, descriptor.getDeclaringClass());
		assertEquals(rawAnnotation, descriptor.getAnnotation());

		descriptor = findAnnotationDescriptorForTypes(SubInheritedAnnotationInterface.class, Transactional.class);
		assertNotNull(descriptor);
		assertEquals(SubInheritedAnnotationInterface.class, descriptor.getRootDeclaringClass());
		assertEquals(InheritedAnnotationInterface.class, descriptor.getDeclaringClass());
		assertEquals(rawAnnotation, descriptor.getAnnotation());

		descriptor = findAnnotationDescriptorForTypes(SubSubInheritedAnnotationInterface.class, Transactional.class);
		assertNotNull(descriptor);
		assertEquals(SubSubInheritedAnnotationInterface.class, descriptor.getRootDeclaringClass());
		assertEquals(InheritedAnnotationInterface.class, descriptor.getDeclaringClass());
		assertEquals(rawAnnotation, descriptor.getAnnotation());
	}
