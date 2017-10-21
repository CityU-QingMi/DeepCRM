	@Test
	void ignoresUniqueIdsOfOtherEngines(LogRecordListener listener) {
		UniqueId uniqueId = UniqueId.forEngine("someEngine");
		EngineDiscoveryRequest request = requestWithSelector(selectUniqueId(uniqueId));

		new UniqueIdSelectorResolver().resolve(request, allClassesPredicate, collector);

		assertNoRequests();
		assertThat(listener.getLogRecords(UniqueIdSelectorResolver.class)).isEmpty();
	}
