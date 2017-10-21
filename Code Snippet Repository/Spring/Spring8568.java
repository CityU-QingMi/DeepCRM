	@Test
	public void findAnnotationDescriptorWithLocalAndMetaComponentAnnotation() throws Exception {
		Class<Component> annotationType = Component.class;
		AnnotationDescriptor<Component> descriptor = findAnnotationDescriptor(HasLocalAndMetaComponentAnnotation.class,
			annotationType);
		assertEquals(HasLocalAndMetaComponentAnnotation.class, descriptor.getRootDeclaringClass());
		assertEquals(annotationType, descriptor.getAnnotationType());
		assertNull(descriptor.getComposedAnnotation());
		assertNull(descriptor.getComposedAnnotationType());
	}
