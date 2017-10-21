	private static <A extends Annotation> void findRepeatableAnnotations(AnnotatedElement element,
			Class<A> annotationType, Class<? extends Annotation> containerType, boolean inherited, Set<A> found,
			Set<Annotation> visited) {

		if (element instanceof Class) {
			Class<?> clazz = (Class<?>) element;

			// Recurse first in order to support top-down semantics for inherited, repeatable annotations.
			if (inherited) {
				Class<?> superclass = clazz.getSuperclass();
				if (superclass != null && superclass != Object.class) {
					findRepeatableAnnotations(superclass, annotationType, containerType, inherited, found, visited);
				}
			}

			// Search on interfaces
			for (Class<?> ifc : clazz.getInterfaces()) {
				if (ifc != Annotation.class) {
					findRepeatableAnnotations(ifc, annotationType, containerType, inherited, found, visited);
				}
			}
		}

		// Find annotations that are directly present or meta-present on directly present annotations.
		findRepeatableAnnotations(element.getDeclaredAnnotations(), annotationType, containerType, inherited, found,
			visited);

		// Find annotations that are indirectly present or meta-present on indirectly present annotations.
		findRepeatableAnnotations(element.getAnnotations(), annotationType, containerType, inherited, found, visited);
	}
