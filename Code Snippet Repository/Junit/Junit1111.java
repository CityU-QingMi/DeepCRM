		@Test
		void requestsClassSelectorsWhenSelectClassesAnnotationIsPresent() throws Exception {

			@SelectClasses({ Short.class, Byte.class })
			class TestCase {
			}

			LauncherDiscoveryRequest request = instantiateRunnerAndCaptureGeneratedRequest(TestCase.class);

			List<ClassSelector> selectors = request.getSelectorsByType(ClassSelector.class);
			assertThat(selectors).hasSize(2);
			assertEquals(Short.class, selectors.get(0).getJavaClass());
			assertEquals(Byte.class, selectors.get(1).getJavaClass());
		}
