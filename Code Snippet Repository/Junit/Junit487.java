		@Test
		void removeWithTypeSafetyAndPrimitiveValueType() {
			String key = "enigma";
			int value = 42;
			store.put(namespace, key, value);

			// The fact that we can declare this as an int suffices for testing the required type.
			int requiredInt = store.remove(namespace, key, int.class);
			assertEquals(value, requiredInt);

			store.put(namespace, key, value);
			// The fact that we can declare this as an Integer suffices for testing the required type.
			Integer requiredInteger = store.get(namespace, key, Integer.class);
			assertEquals(value, requiredInteger.intValue());
		}
