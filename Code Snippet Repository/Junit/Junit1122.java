		@Test
		void requestsPackageSelectorsWhenPackagesAnnotationIsPresent() throws Exception {

			@SelectPackages({ "foo", "bar" })
			class TestCase {
			}

			LauncherDiscoveryRequest request = instantiateRunnerAndCaptureGeneratedRequest(TestCase.class);

			List<PackageSelector> selectors = request.getSelectorsByType(PackageSelector.class);
			assertThat(selectors).hasSize(2);
			assertEquals("foo", selectors.get(0).getPackageName());
			assertEquals("bar", selectors.get(1).getPackageName());
		}
