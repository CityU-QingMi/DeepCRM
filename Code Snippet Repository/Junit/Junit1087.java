		@Test
		void classesAreStoredInDiscoveryRequest() {
			// @formatter:off
			LauncherDiscoveryRequest discoveryRequest = request()
					.selectors(
							selectClass(LauncherDiscoveryRequestBuilderTests.class.getName()),
							selectClass(SampleTestClass.class)
					)
				.build();
			// @formatter:on

			List<Class<?>> classes = discoveryRequest.getSelectorsByType(ClassSelector.class).stream().map(
				ClassSelector::getJavaClass).collect(toList());
			assertThat(classes).contains(SampleTestClass.class, LauncherDiscoveryRequestBuilderTests.class);
		}
