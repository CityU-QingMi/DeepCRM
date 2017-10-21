	@Test
	@SuppressWarnings("")
	public void findAnnotationDescriptorForTypesWithLocalAndMetaComponentAnnotation() throws Exception {
		Class<Component> annotationType = Component.class;
		UntypedAnnotationDescriptor descriptor = findAnnotationDescriptorForTypes(
			HasLocalAndMetaComponentAnnotation.class, Transactional.class, annotationType, Order.class);
		assertEquals(HasLocalAndMetaComponentAnnotation.class, descriptor.getRootDeclaringClass());
		assertEquals(annotationType, descriptor.getAnnotationType());
		assertNull(descriptor.getComposedAnnotation());
		assertNull(descriptor.getComposedAnnotationType());
	}
