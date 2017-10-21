	@Test
	public void findAnnotationDescriptorForNonInheritedAnnotationOnInterface() throws Exception {
		// Note: @Order is not inherited.
		Order rawAnnotation = NonInheritedAnnotationInterface.class.getAnnotation(Order.class);

		AnnotationDescriptor<Order> descriptor;

		descriptor = findAnnotationDescriptor(NonInheritedAnnotationInterface.class, Order.class);
		assertNotNull(descriptor);
		assertEquals(NonInheritedAnnotationInterface.class, descriptor.getRootDeclaringClass());
		assertEquals(NonInheritedAnnotationInterface.class, descriptor.getDeclaringClass());
		assertEquals(rawAnnotation, descriptor.getAnnotation());

		descriptor = findAnnotationDescriptor(SubNonInheritedAnnotationInterface.class, Order.class);
		assertNotNull(descriptor);
		assertEquals(SubNonInheritedAnnotationInterface.class, descriptor.getRootDeclaringClass());
		assertEquals(NonInheritedAnnotationInterface.class, descriptor.getDeclaringClass());
		assertEquals(rawAnnotation, descriptor.getAnnotation());
	}
