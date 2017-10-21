		@Test
		void addsSingleExplicitClassNameFilterToRequestWhenIncludeClassNamePatternsAnnotationIsPresent()
				throws Exception {

			@IncludeClassNamePatterns(".*Foo")
			class TestCase {
			}

			LauncherDiscoveryRequest request = instantiateRunnerAndCaptureGeneratedRequest(TestCase.class);

			List<ClassNameFilter> filters = request.getFiltersByType(ClassNameFilter.class);
			assertThat(getOnlyElement(filters).toString()).contains(".*Foo");
		}
