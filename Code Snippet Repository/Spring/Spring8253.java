	@Nullable
	private static <T extends Annotation> AnnotationDescriptor<T> findAnnotationDescriptor(
			@Nullable Class<?> clazz, Set<Annotation> visited, Class<T> annotationType) {

		Assert.notNull(annotationType, "Annotation type must not be null");
		if (clazz == null || Object.class == clazz) {
			return null;
		}

		// Declared locally?
		if (AnnotationUtils.isAnnotationDeclaredLocally(annotationType, clazz)) {
			return new AnnotationDescriptor<>(clazz, clazz.getAnnotation(annotationType));
		}

		// Declared on a composed annotation (i.e., as a meta-annotation)?
		for (Annotation composedAnn : clazz.getDeclaredAnnotations()) {
			Class<? extends Annotation> composedType = composedAnn.annotationType();
			if (!AnnotationUtils.isInJavaLangAnnotationPackage(composedType.getName()) && visited.add(composedAnn)) {
				AnnotationDescriptor<T> descriptor = findAnnotationDescriptor(composedType, visited, annotationType);
				if (descriptor != null) {
					return new AnnotationDescriptor<>(
							clazz, descriptor.getDeclaringClass(), composedAnn, descriptor.getAnnotation());
				}
			}
		}

		// Declared on interface?
		for (Class<?> ifc : clazz.getInterfaces()) {
			AnnotationDescriptor<T> descriptor = findAnnotationDescriptor(ifc, visited, annotationType);
			if (descriptor != null) {
				return new AnnotationDescriptor<>(clazz, descriptor.getDeclaringClass(),
						descriptor.getComposedAnnotation(), descriptor.getAnnotation());
			}
		}

		// Declared on a superclass?
		return findAnnotationDescriptor(clazz.getSuperclass(), visited, annotationType);
	}
