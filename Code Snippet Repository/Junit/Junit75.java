	@Override
	public Optional<TestDescriptor> resolveUniqueId(UniqueId.Segment segment, TestDescriptor parent) {
		if (!segment.getType().equals(this.segmentType)) {
			return Optional.empty();
		}

		if (!(parent instanceof ClassTestDescriptor)) {
			return Optional.empty();
		}

		Optional<Method> optionalMethod = findMethod(segment, (ClassTestDescriptor) parent);
		if (!optionalMethod.isPresent()) {
			return Optional.empty();
		}

		Method method = optionalMethod.get();
		if (!isRelevantMethod(method)) {
			return Optional.empty();
		}

		return Optional.of(createTestDescriptor(parent, method));
	}
