		@Test
		@SuppressWarnings("")
		void discoveryFiltersAreStoredInDiscoveryRequest() {
			// @formatter:off
			LauncherDiscoveryRequest discoveryRequest = request()
					.filters(
							new DiscoveryFilterStub("filter1"),
							new DiscoveryFilterStub("filter2")
					).build();
			// @formatter:on

			List<String> filterStrings = discoveryRequest.getFiltersByType(DiscoveryFilter.class).stream().map(
				DiscoveryFilter::toString).collect(toList());
			assertThat(filterStrings).hasSize(2);
			assertThat(filterStrings).contains("filter1", "filter2");
		}
