		@Test
		void addsMultipleClassNameFilterToRequestWhenExcludeClassNamePatternsAnnotationIsPresent() throws Exception {

			@ExcludeClassNamePatterns({ ".*Foo", "Bar.*" })
			class TestCase {
			}

			LauncherDiscoveryRequest request = instantiateRunnerAndCaptureGeneratedRequest(TestCase.class);

			List<ClassNameFilter> filters = request.getFiltersByType(ClassNameFilter.class);
			assertThat(getOnlyElement(filters).toString()).contains(".*Foo", "Bar.*");
		}
