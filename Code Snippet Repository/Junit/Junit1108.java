		@Test
		void doesNotAddClassNameFilterWhenExcludeClassNamePatternsAnnotationIsPresentWithEmptyArguments()
				throws Exception {

			@ExcludeClassNamePatterns({})
			class TestCase {
			}

			LauncherDiscoveryRequest request = instantiateRunnerAndCaptureGeneratedRequest(TestCase.class);

			List<ClassNameFilter> filters = request.getFiltersByType(ClassNameFilter.class);
			assertThat(filters).isEmpty();
		}
