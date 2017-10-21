		@Test
		void keyIsOnlyRemovedInGivenNamespace() {
			Namespace namespace1 = Namespace.create("ns1");
			Namespace namespace2 = Namespace.create("ns2");

			Object value1 = createObject("value1");
			Object value2 = createObject("value2");

			store.put(namespace1, key, value1);
			store.put(namespace2, key, value2);
			store.remove(namespace1, key);

			assertNull(store.get(namespace1, key));
			assertEquals(value2, store.get(namespace2, key));
		}
