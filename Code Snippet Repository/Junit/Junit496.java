		@Test
		void getOrComputeIfAbsentWithTypeSafetyAndInvalidRequiredTypeThrowsException() {
			String key = "pi";
			Float value = Float.valueOf(3.14f);

			// Store a Float...
			store.put(namespace, key, value);

			// But declare that our function creates a String...
			Function<String, String> defaultCreator = k -> "enigma";

			Exception exception = assertThrows(ExtensionContextException.class,
				() -> store.getOrComputeIfAbsent(namespace, key, defaultCreator, String.class));
			assertEquals("Object stored under key [pi] is not of required type [java.lang.String]",
				exception.getMessage());
		}
