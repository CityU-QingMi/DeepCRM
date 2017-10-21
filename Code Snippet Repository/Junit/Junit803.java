	private TestDescriptor findChildByDisplayName(TestDescriptor runnerDescriptor, String displayName) {
		// @formatter:off
		Set<? extends TestDescriptor> children = runnerDescriptor.getChildren();
		return children
				.stream()
				.filter(where(TestDescriptor::getDisplayName, isEqual(displayName)))
				.findAny()
				.orElseThrow(() ->
					new AssertionError(format("No child with display name \"{0}\" in {1}", displayName, children)));
		// @formatter:on
	}
