		@Test
		void packagesAreStoredInDiscoveryRequest() {
			// @formatter:off
			LauncherDiscoveryRequest discoveryRequest = request()
					.selectors(
							selectPackage("org.junit.platform.engine")
					).build();
			// @formatter:on

			List<String> packageSelectors = discoveryRequest.getSelectorsByType(PackageSelector.class).stream().map(
				PackageSelector::getPackageName).collect(toList());
			assertThat(packageSelectors).contains("org.junit.platform.engine");
		}
