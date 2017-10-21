		@Test
		void addsPackageFiltersToRequestWhenExcludePackageAnnotationIsPresent() throws Exception {

			@ExcludePackages({ "excludedpackage1", "excludedpackage2" })
			class TestCase {
			}

			LauncherDiscoveryRequest request = instantiateRunnerAndCaptureGeneratedRequest(TestCase.class);

			List<PackageNameFilter> filters = request.getFiltersByType(PackageNameFilter.class);
			assertThat(filters).hasSize(1);

			PackageNameFilter filter = filters.get(0);
			assertTrue(filter.apply("includedpackage1.TestClass").included());
			assertTrue(filter.apply("excludedpackage1.TestClass").excluded());
			assertTrue(filter.apply("excludedpackage2.TestClass").excluded());
		}
