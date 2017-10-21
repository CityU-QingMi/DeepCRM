	@Test
	void displayNamesIgnoredInReport() throws NoSuchMethodException {
		TestMethodTestDescriptor descriptor = new TestMethodTestDescriptor(newId(), MyTestClass.class,
			MyTestClass.class.getDeclaredMethod("myNamedTestMethod"));

		TestIdentifier factoryIdentifier = TestIdentifier.from(descriptor);
		ArgumentCaptor<ReportEntry> entryCaptor = ArgumentCaptor.forClass(ReportEntry.class);

		adapter.executionSkipped(factoryIdentifier, "");
		verify(listener).testSkipped(entryCaptor.capture());

		ReportEntry value = entryCaptor.getValue();

		assertEquals("myNamedTestMethod()", value.getName());
	}
