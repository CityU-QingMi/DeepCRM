	public static <A extends Annotation> List<A> findRepeatableAnnotations(AnnotatedElement element,
			Class<A> annotationType) {

		Preconditions.notNull(annotationType, "annotationType must not be null");
		Repeatable repeatable = annotationType.getAnnotation(Repeatable.class);
		Preconditions.notNull(repeatable, () -> annotationType.getName() + " must be @Repeatable");
		Class<? extends Annotation> containerType = repeatable.value();
		boolean inherited = containerType.isAnnotationPresent(Inherited.class);

		// Short circuit the search algorithm.
		if (element == null) {
			return Collections.emptyList();
		}

		// We use a LinkedHashSet because the search algorithm may discover
		// duplicates, but we need to maintain the original order.
		Set<A> found = new LinkedHashSet<>(16);
		findRepeatableAnnotations(element, annotationType, containerType, inherited, found, new HashSet<>(16));
		// unmodifiable since returned from public, non-internal method(s)
		return Collections.unmodifiableList(new ArrayList<>(found));
	}
