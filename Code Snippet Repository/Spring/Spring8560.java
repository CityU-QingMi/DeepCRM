	@Test
	@SuppressWarnings("")
	public void findAnnotationDescriptorForTypesForNonInheritedAnnotationOnInterface() throws Exception {
		// Note: @Order is not inherited.
		Order rawAnnotation = NonInheritedAnnotationInterface.class.getAnnotation(Order.class);

		UntypedAnnotationDescriptor descriptor;

		descriptor = findAnnotationDescriptorForTypes(NonInheritedAnnotationInterface.class, Order.class);
		assertNotNull(descriptor);
		assertEquals(NonInheritedAnnotationInterface.class, descriptor.getRootDeclaringClass());
		assertEquals(NonInheritedAnnotationInterface.class, descriptor.getDeclaringClass());
		assertEquals(rawAnnotation, descriptor.getAnnotation());

		descriptor = findAnnotationDescriptorForTypes(SubNonInheritedAnnotationInterface.class, Order.class);
		assertNotNull(descriptor);
		assertEquals(SubNonInheritedAnnotationInterface.class, descriptor.getRootDeclaringClass());
		assertEquals(NonInheritedAnnotationInterface.class, descriptor.getDeclaringClass());
		assertEquals(rawAnnotation, descriptor.getAnnotation());
	}
