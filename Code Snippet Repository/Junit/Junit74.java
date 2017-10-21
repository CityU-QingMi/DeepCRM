	@Override
	public Set<TestDescriptor> resolveElement(AnnotatedElement element, TestDescriptor parent) {
		if (!(element instanceof Method)) {
			return Collections.emptySet();
		}

		if (!(parent instanceof ClassTestDescriptor)) {
			return Collections.emptySet();
		}

		Method method = (Method) element;
		if (!isRelevantMethod(method)) {
			return Collections.emptySet();
		}

		return Collections.singleton(createTestDescriptor(parent, method));
	}
