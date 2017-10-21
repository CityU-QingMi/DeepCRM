		@Test
		void requestsClassSelectorForAnnotatedClassWhenNoAdditionalAnnotationsArePresent() throws Exception {

			class TestCase {
			}

			LauncherDiscoveryRequest request = instantiateRunnerAndCaptureGeneratedRequest(TestCase.class);

			List<ClassSelector> selectors = request.getSelectorsByType(ClassSelector.class);
			assertThat(selectors).hasSize(1);
			ClassSelector classSelector = getOnlyElement(selectors);
			assertEquals(TestCase.class, classSelector.getJavaClass());
		}
