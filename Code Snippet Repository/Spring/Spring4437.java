	@Nullable
	private static <A extends Annotation> A findAnnotation(
			AnnotatedElement annotatedElement, Class<A> annotationType, Set<Annotation> visited) {
		try {
			A annotation = annotatedElement.getDeclaredAnnotation(annotationType);
			if (annotation != null) {
				return annotation;
			}
			for (Annotation declaredAnn : annotatedElement.getDeclaredAnnotations()) {
				Class<? extends Annotation> declaredType = declaredAnn.annotationType();
				if (!isInJavaLangAnnotationPackage(declaredType) && visited.add(declaredAnn)) {
					annotation = findAnnotation((AnnotatedElement) declaredType, annotationType, visited);
					if (annotation != null) {
						return annotation;
					}
				}
			}
		}
		catch (Throwable ex) {
			handleIntrospectionFailure(annotatedElement, ex);
		}
		return null;
	}
