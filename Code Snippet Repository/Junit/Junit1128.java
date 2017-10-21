		@Test
		void addsDefaultClassNameFilterToRequestWhenFilterClassNameAnnotationIsNotPresentOnTestSuite()
				throws Exception {

			@SelectPackages("foo")
			class TestCase {
			}

			LauncherDiscoveryRequest request = instantiateRunnerAndCaptureGeneratedRequest(TestCase.class);

			List<ClassNameFilter> filters = request.getFiltersByType(ClassNameFilter.class);
			assertThat(getOnlyElement(filters).toString()).contains(STANDARD_INCLUDE_PATTERN);
		}
