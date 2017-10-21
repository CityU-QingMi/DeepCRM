	@Test
	void logsWarningOnUnexpectedTestDescriptor(LogRecordListener listener) {
		UniqueId uniqueId = UniqueId.forEngine(ENGINE_ID).append("wrong-type", "foo:bar");
		EngineDiscoveryRequest request = requestWithSelector(selectUniqueId(uniqueId));

		new UniqueIdSelectorResolver().resolve(request, allClassesPredicate, collector);

		assertNoRequests();
		assertLoggedWarning(listener, "Unresolvable Unique ID (" + uniqueId
				+ "): Unique ID segment after engine segment must be of type \"runner\"");
	}
