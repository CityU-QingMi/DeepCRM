	@Test
	void logsWarningOnUnloadableTestClass(LogRecordListener listener) {
		UniqueId uniqueId = VintageUniqueIdBuilder.uniqueIdForClass("foo.bar.UnknownClass");
		EngineDiscoveryRequest request = requestWithSelector(selectUniqueId(uniqueId));

		new UniqueIdSelectorResolver().resolve(request, allClassesPredicate, collector);

		assertNoRequests();
		assertLoggedWarning(listener, "Unresolvable Unique ID (" + uniqueId + "): Unknown class foo.bar.UnknownClass");
	}
