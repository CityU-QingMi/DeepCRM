	private static TestIdentifier newSourcelessIdentifierWithParent(TestPlan testPlan, String parentDisplay) {
		// A parent test identifier with a name.
		TestDescriptor parent = mock(TestDescriptor.class);
		when(parent.getUniqueId()).thenReturn(newId());
		when(parent.getDisplayName()).thenReturn(parentDisplay);
		when(parent.getLegacyReportingName()).thenReturn(parentDisplay);
		when(parent.getType()).thenReturn(TestDescriptor.Type.CONTAINER);
		TestIdentifier parentId = TestIdentifier.from(parent);

		// The (child) test case that is to be executed as part of a test plan.
		TestDescriptor child = mock(TestDescriptor.class);
		when(child.getUniqueId()).thenReturn(newId());
		when(child.getType()).thenReturn(TestDescriptor.Type.TEST);

		// Ensure the child source is null yet that there is a parent -- the special case to be tested.
		when(child.getSource()).thenReturn(Optional.empty());
		when(child.getParent()).thenReturn(Optional.of(parent));
		TestIdentifier childId = TestIdentifier.from(child);

		testPlan.add(childId);
		testPlan.add(parentId);

		return childId;
	}
