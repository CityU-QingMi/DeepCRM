	@Nullable
	private static <T> T searchOnInterfaces(Method method, @Nullable Class<? extends Annotation> annotationType,
			@Nullable String annotationName, @Nullable Class<? extends Annotation> containerType,
			Processor<T> processor, Set<AnnotatedElement> visited, int metaDepth, Class<?>[] ifcs) {

		for (Class<?> iface : ifcs) {
			if (AnnotationUtils.isInterfaceWithAnnotatedMethods(iface)) {
				try {
					Method equivalentMethod = iface.getMethod(method.getName(), method.getParameterTypes());
					T result = searchWithFindSemantics(equivalentMethod, annotationType, annotationName, containerType,
							processor, visited, metaDepth);
					if (result != null) {
						return result;
					}
				}
				catch (NoSuchMethodException ex) {
					// Skip this interface - it doesn't have the method...
				}
			}
		}

		return null;
	}
