		@Test
		void valueIsComputedIfAbsentInDifferentNamespace() {
			Namespace namespace1 = Namespace.create("ns1");
			Namespace namespace2 = Namespace.create("ns2");

			assertEquals(value, store.getOrComputeIfAbsent(namespace1, key, innerKey -> value));
			assertEquals(value, store.get(namespace1, key));

			assertNull(store.get(namespace2, key));
		}
