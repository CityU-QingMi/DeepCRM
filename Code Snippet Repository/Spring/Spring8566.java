	@Test
	public void findAnnotationDescriptorWithInheritedAnnotationOnInterface() throws Exception {
		// Note: @Transactional is inherited
		Transactional rawAnnotation = InheritedAnnotationInterface.class.getAnnotation(Transactional.class);

		AnnotationDescriptor<Transactional> descriptor;

		descriptor = findAnnotationDescriptor(InheritedAnnotationInterface.class, Transactional.class);
		assertNotNull(descriptor);
		assertEquals(InheritedAnnotationInterface.class, descriptor.getRootDeclaringClass());
		assertEquals(InheritedAnnotationInterface.class, descriptor.getDeclaringClass());
		assertEquals(rawAnnotation, descriptor.getAnnotation());

		descriptor = findAnnotationDescriptor(SubInheritedAnnotationInterface.class, Transactional.class);
		assertNotNull(descriptor);
		assertEquals(SubInheritedAnnotationInterface.class, descriptor.getRootDeclaringClass());
		assertEquals(InheritedAnnotationInterface.class, descriptor.getDeclaringClass());
		assertEquals(rawAnnotation, descriptor.getAnnotation());

		descriptor = findAnnotationDescriptor(SubSubInheritedAnnotationInterface.class, Transactional.class);
		assertNotNull(descriptor);
		assertEquals(SubSubInheritedAnnotationInterface.class, descriptor.getRootDeclaringClass());
		assertEquals(InheritedAnnotationInterface.class, descriptor.getDeclaringClass());
		assertEquals(rawAnnotation, descriptor.getAnnotation());
	}
