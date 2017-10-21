		@Test
		void addsTagFilterToRequestWhenIncludeTagsAnnotationIsPresent() throws Exception {

			@IncludeTags({ "foo", "bar" })
			class TestCase {
			}

			LauncherDiscoveryRequest request = instantiateRunnerAndCaptureGeneratedRequest(TestCase.class);

			List<PostDiscoveryFilter> filters = request.getPostDiscoveryFilters();
			assertThat(filters).hasSize(1);

			PostDiscoveryFilter filter = filters.get(0);
			assertTrue(filter.apply(testDescriptorWithTag("foo")).included());
			assertTrue(filter.apply(testDescriptorWithTag("bar")).included());
			assertTrue(filter.apply(testDescriptorWithTag("baz")).excluded());
		}
