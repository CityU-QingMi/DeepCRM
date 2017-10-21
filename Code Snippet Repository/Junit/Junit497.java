		@Test
		void getOrComputeIfAbsentWithTypeSafetyAndPrimitiveValueType() {
			String key = "enigma";
			int value = 42;

			// The fact that we can declare this as an int/Integer suffices for testing the required type.
			int computedInt = store.getOrComputeIfAbsent(namespace, key, k -> value, int.class);
			Integer computedInteger = store.getOrComputeIfAbsent(namespace, key, k -> value, Integer.class);
			assertEquals(value, computedInt);
			assertEquals(value, computedInteger.intValue());
		}
