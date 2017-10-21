	@Override
	public Optional<TestDescriptor> resolveUniqueId(UniqueId.Segment segment, TestDescriptor parent) {

		if (!segment.getType().equals(getSegmentType())) {
			return Optional.empty();
		}

		if (!requiredParentType().isInstance(parent)) {
			return Optional.empty();
		}

		String className = getClassName(parent, segment.getValue());

		Optional<Class<?>> optionalContainerClass = ReflectionUtils.loadClass(className);
		if (!optionalContainerClass.isPresent()) {
			return Optional.empty();
		}

		Class<?> containerClass = optionalContainerClass.get();
		if (!isPotentialCandidate(containerClass)) {
			return Optional.empty();
		}

		UniqueId uniqueId = createUniqueId(containerClass, parent);
		return Optional.of(resolveClass(containerClass, uniqueId));
	}
