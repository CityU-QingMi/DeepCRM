	@Test
	void argumentsRethrowsOriginalExceptionFromProviderAsUncheckedException() {
		ArgumentsProvider failingProvider = (context) -> {
			throw new FileNotFoundException("a message");
		};

		FileNotFoundException exception = assertThrows(FileNotFoundException.class,
			() -> arguments(failingProvider, null));
		assertEquals("a message", exception.getMessage());
	}
