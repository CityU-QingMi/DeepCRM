	private void addCategoriesAsTags(Set<TestTag> tags) {
		Category annotation = description.getAnnotation(Category.class);
		if (annotation != null) {
			// @formatter:off
			stream(annotation.value())
					.map(ReflectionUtils::getAllAssignmentCompatibleClasses)
					.flatMap(Collection::stream)
					.distinct()
					.map(Class::getName)
					.map(TestTag::create)
					.forEachOrdered(tags::add);
			// @formatter:on
		}
	}
