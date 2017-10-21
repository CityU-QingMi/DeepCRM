		@Test
		void doesNotAddClassNameFilterWhenIncludeClassNamePatternsAnnotationIsPresentWithEmptyArguments()
				throws Exception {

			@IncludeClassNamePatterns({})
			class TestCase {
			}

			LauncherDiscoveryRequest request = instantiateRunnerAndCaptureGeneratedRequest(TestCase.class);

			List<ClassNameFilter> filters = request.getFiltersByType(ClassNameFilter.class);
			assertThat(filters).isEmpty();
		}
