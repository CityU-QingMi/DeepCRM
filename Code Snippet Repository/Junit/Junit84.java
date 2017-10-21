	@Override
	public Set<TestDescriptor> resolveElement(AnnotatedElement element, TestDescriptor parent) {
		if (!(element instanceof Class)) {
			return Collections.emptySet();
		}

		Class<?> clazz = (Class<?>) element;
		if (!isPotentialCandidate(clazz)) {
			return Collections.emptySet();
		}

		UniqueId uniqueId = createUniqueId(clazz, parent);
		return Collections.singleton(resolveClass(clazz, uniqueId));
	}
