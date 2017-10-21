	private void assertAtComponentOnComposedAnnotation(Class<?> startClass, Class<?> rootDeclaringClass,
			Class<?> declaringClass, String name, Class<? extends Annotation> composedAnnotationType) {
		AnnotationDescriptor<Component> descriptor = findAnnotationDescriptor(startClass, Component.class);
		assertNotNull("AnnotationDescriptor should not be null", descriptor);
		assertEquals("rootDeclaringClass", rootDeclaringClass, descriptor.getRootDeclaringClass());
		assertEquals("declaringClass", declaringClass, descriptor.getDeclaringClass());
		assertEquals("annotationType", Component.class, descriptor.getAnnotationType());
		assertEquals("component name", name, descriptor.getAnnotation().value());
		assertNotNull("composedAnnotation should not be null", descriptor.getComposedAnnotation());
		assertEquals("composedAnnotationType", composedAnnotationType, descriptor.getComposedAnnotationType());
	}
