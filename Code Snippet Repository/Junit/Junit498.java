		@Test
		void removeWithTypeSafetyAndInvalidRequiredTypeThrowsException() {
			Integer key = 42;
			String value = "enigma";
			store.put(namespace, key, value);

			Exception exception = assertThrows(ExtensionContextException.class,
				() -> store.remove(namespace, key, Number.class));
			assertEquals("Object stored under key [42] is not of required type [java.lang.Number]",
				exception.getMessage());
		}
