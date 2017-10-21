	@Test
	public void testWithNonInterface() throws Exception {
		try {
			getWithMapping("JmxTestBean");
			fail("Should have thrown IllegalArgumentException");
		}
		catch (IllegalArgumentException ex) {
			// expected
		}
	}
