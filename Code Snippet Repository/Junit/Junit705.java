	@API(status = INTERNAL, since = "")
	public static TestIdentifier from(TestDescriptor testDescriptor) {
		Preconditions.notNull(testDescriptor, "TestDescriptor must not be null");
		String uniqueId = testDescriptor.getUniqueId().toString();
		String displayName = testDescriptor.getDisplayName();
		TestSource source = testDescriptor.getSource().orElse(null);
		Set<TestTag> tags = testDescriptor.getTags();
		Type type = testDescriptor.getType();
		String parentId = testDescriptor.getParent().map(
			parentDescriptor -> parentDescriptor.getUniqueId().toString()).orElse(null);
		String legacyReportingName = testDescriptor.getLegacyReportingName();
		return new TestIdentifier(uniqueId, displayName, source, tags, type, parentId, legacyReportingName);
	}
