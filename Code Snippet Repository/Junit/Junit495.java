		@Test
		void getWithTypeSafetyAndPrimitiveValueType() {
			String key = "enigma";
			int value = 42;
			store.put(namespace, key, value);

			// The fact that we can declare this as an int/Integer suffices for testing the required type.
			int requiredInt = store.get(namespace, key, int.class);
			Integer requiredInteger = store.get(namespace, key, Integer.class);
			assertEquals(value, requiredInt);
			assertEquals(value, requiredInteger.intValue());
		}
