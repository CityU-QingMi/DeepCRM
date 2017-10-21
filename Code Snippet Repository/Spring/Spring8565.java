	@SuppressWarnings("")
	private void assertAtComponentOnComposedAnnotationForMultipleCandidateTypes(Class<?> startClass,
			Class<?> rootDeclaringClass, Class<?> declaringClass, String name,
			Class<? extends Annotation> composedAnnotationType) {
		Class<Component> annotationType = Component.class;
		UntypedAnnotationDescriptor descriptor = findAnnotationDescriptorForTypes(startClass, Service.class,
			annotationType, Order.class, Transactional.class);
		assertNotNull("UntypedAnnotationDescriptor should not be null", descriptor);
		assertEquals("rootDeclaringClass", rootDeclaringClass, descriptor.getRootDeclaringClass());
		assertEquals("declaringClass", declaringClass, descriptor.getDeclaringClass());
		assertEquals("annotationType", annotationType, descriptor.getAnnotationType());
		assertEquals("component name", name, ((Component) descriptor.getAnnotation()).value());
		assertNotNull("composedAnnotation should not be null", descriptor.getComposedAnnotation());
		assertEquals("composedAnnotationType", composedAnnotationType, descriptor.getComposedAnnotationType());
	}
