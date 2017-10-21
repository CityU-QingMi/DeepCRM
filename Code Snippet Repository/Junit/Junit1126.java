		@Test
		void addsTagFilterToRequestWhenExcludeTagsAnnotationIsPresent() throws Exception {

			@ExcludeTags({ "foo", "bar" })
			class TestCase {
			}

			LauncherDiscoveryRequest request = instantiateRunnerAndCaptureGeneratedRequest(TestCase.class);

			List<PostDiscoveryFilter> filters = request.getPostDiscoveryFilters();
			assertThat(filters).hasSize(1);

			PostDiscoveryFilter filter = filters.get(0);
			assertTrue(filter.apply(testDescriptorWithTag("foo")).excluded());
			assertTrue(filter.apply(testDescriptorWithTag("bar")).excluded());
			assertTrue(filter.apply(testDescriptorWithTag("baz")).included());
		}
