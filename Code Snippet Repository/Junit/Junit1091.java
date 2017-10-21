		@Test
		void uniqueIdsAreStoredInDiscoveryRequest() {
			UniqueId id1 = UniqueId.forEngine("engine").append("foo", "id1");
			UniqueId id2 = UniqueId.forEngine("engine").append("foo", "id2");

			// @formatter:off
			LauncherDiscoveryRequest discoveryRequest = request()
					.selectors(
							selectUniqueId(id1),
							selectUniqueId(id2)
					).build();
			// @formatter:on

			List<String> uniqueIds = discoveryRequest.getSelectorsByType(UniqueIdSelector.class).stream().map(
				UniqueIdSelector::getUniqueId).map(Object::toString).collect(toList());

			assertThat(uniqueIds).contains(id1.toString(), id2.toString());
		}
