	@Test
	public void createWithNullTarget() {
		try {
			createAccessor(null);
			fail("Must throw an exception when constructed with null object");
		}
		catch (IllegalArgumentException ex) {
			// expected
		}
	}
