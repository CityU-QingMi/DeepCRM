		@Test
		void addsSingleClassNameFilterToRequestWhenExcludeClassNamePatternsAnnotationIsPresent() throws Exception {

			@ExcludeClassNamePatterns(".*Foo")
			class TestCase {
			}

			LauncherDiscoveryRequest request = instantiateRunnerAndCaptureGeneratedRequest(TestCase.class);

			List<ClassNameFilter> filters = request.getFiltersByType(ClassNameFilter.class);
			assertThat(getOnlyElement(filters).toString()).contains(".*Foo");
		}
