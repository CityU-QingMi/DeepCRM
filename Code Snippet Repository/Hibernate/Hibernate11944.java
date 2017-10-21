	private String createFailureMessage(int id, Geometry storedGeometry, Geometry retrievedGeometry) {
		String expectedText = ( storedGeometry != null ? storedGeometry.toString() : "NULL" );
		String retrievedText = ( retrievedGeometry != null ? retrievedGeometry.toString() : "NULL" );
		return String.format(
				"Equality testsuite-suite failed for %d.%nExpected: %s%nReceived:%s",
				id,
				expectedText,
				retrievedText
		);
	}
