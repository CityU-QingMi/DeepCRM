		@Test
		void removeNullValueWithTypeSafety() {
			Integer key = 42;
			store.put(namespace, key, null);

			// The fact that we can declare this as a String suffices for testing the required type.
			String removedValue = store.remove(namespace, key, String.class);
			assertNull(removedValue);
			assertNull(store.get(namespace, key));
		}
