	@Test
	void logsWarningForEngineUniqueId(LogRecordListener listener) {
		String uniqueId = engineId().toString();
		EngineDiscoveryRequest request = requestWithSelector(selectUniqueId(uniqueId));

		new UniqueIdSelectorResolver().resolve(request, allClassesPredicate, collector);

		assertNoRequests();
		assertLoggedWarning(listener,
			"Unresolvable Unique ID (" + engineId() + "): Cannot resolve the engine's unique ID");
	}
