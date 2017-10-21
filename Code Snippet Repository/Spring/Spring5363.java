	@Test
	public void testSignificantLoad() throws Exception {
		Assume.group(TestGroup.LONG_RUNNING);

		// the biggest public class in the JDK (>60k)
		URL url = getClass().getResource("/java/awt/Component.class");
		assertThat(url, notNullValue());

		// look at a LOT of items
		for (int i = 0; i < ITEMS_TO_LOAD; i++) {
			Resource resource = new UrlResource(url) {

				@Override
				public boolean equals(Object obj) {
					return (obj == this);
				}

				@Override
				public int hashCode() {
					return System.identityHashCode(this);
				}
			};

			MetadataReader reader = mrf.getMetadataReader(resource);
			assertThat(reader, notNullValue());
		}

		// useful for profiling to take snapshots
		// System.in.read();
	}
