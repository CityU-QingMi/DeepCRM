	private void assertEqualsAndHashCodeContracts(Object master, Object equal, Object notEqual, Object subclass) {
		assertEquals("Should be equal", master, equal);
		assertEquals("Hash code for equal instances should match", master.hashCode(), equal.hashCode());

		assertNotEquals("Should not be equal", master, notEqual);
		assertNotEquals("Hash code for non-equal instances should not match", master.hashCode(), notEqual.hashCode());

		assertEquals("Subclass should be equal", master, subclass);
		assertEquals("Hash code for subclass should match", master.hashCode(), subclass.hashCode());
	}
