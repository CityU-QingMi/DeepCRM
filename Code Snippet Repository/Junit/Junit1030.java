	@Override
	public Set<TestTag> getTags() {
		// Copied from org.junit.jupiter.engine.descriptor.JupiterTestDescriptor.getTags(AnnotatedElement)
		// @formatter:off
		return findRepeatableAnnotations(this.testClass, Tag.class).stream()
				.map(Tag::value)
				.filter(tag -> {
					boolean isValid = TestTag.isValid(tag);
					if (!isValid) {
						// TODO [#242] Replace logging with precondition check once we have a proper mechanism for
						// handling validation exceptions during the TestEngine discovery phase.
						//
						// As an alternative to a precondition check here, we could catch any
						// PreconditionViolationException thrown by TestTag::create.
						logger.warn(() -> String.format(
							"Configuration error: invalid tag syntax in @Tag(\"%s\") declaration on [%s]. Tag will be ignored.",
							tag, this.testClass));
					}
					return isValid;
				})
				.map(TestTag::create)
				.collect(toCollection(LinkedHashSet::new));
		// @formatter:on
	}
