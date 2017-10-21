	@Test
	void assertTimeoutPreemptivelyForSupplierThatThrowsAnException() {
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			assertTimeoutPreemptively(ofMillis(500), () -> {
				ExceptionUtils.throwAsUncheckedException(new RuntimeException("not this time"));
				return "Tempus Fugit";
			});
			fail("exception should be thrown");
		});
		assertMessageEquals(exception, "not this time");
	}
