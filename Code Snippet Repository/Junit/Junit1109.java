		@Test
		void trimsArgumentsOfIncludeClassNamePatternsAnnotation() throws Exception {

			@IncludeClassNamePatterns({ " foo", "bar " })
			class TestCase {
			}

			LauncherDiscoveryRequest request = instantiateRunnerAndCaptureGeneratedRequest(TestCase.class);

			List<ClassNameFilter> filters = request.getFiltersByType(ClassNameFilter.class);
			assertThat(getOnlyElement(filters).toString()).contains("'foo'", "'bar'");
		}
