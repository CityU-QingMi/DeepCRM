	private int resolveUniqueId(TestDescriptor parent, List<Segment> remainingSegments) {
		if (remainingSegments.isEmpty()) {
			resolveChildren(parent);
			return 0;
		}

		Segment head = remainingSegments.remove(0);
		for (ElementResolver resolver : this.resolvers) {
			Optional<TestDescriptor> resolvedDescriptor = resolver.resolveUniqueId(head, parent);
			if (!resolvedDescriptor.isPresent()) {
				continue;
			}

			Optional<TestDescriptor> foundTestDescriptor = findTestDescriptorByUniqueId(
				resolvedDescriptor.get().getUniqueId());
			TestDescriptor descriptor = foundTestDescriptor.orElseGet(() -> {
				TestDescriptor newDescriptor = resolvedDescriptor.get();
				parent.addChild(newDescriptor);
				return newDescriptor;
			});
			return 1 + resolveUniqueId(descriptor, remainingSegments);
		}

		return 0;
	}
