	@Test
	public void testWithUnknownClass() throws Exception {
		try {
			getWithMapping("com.foo.bar.Unknown");
			fail("Should have thrown IllegalArgumentException");
		}
		catch (IllegalArgumentException ex) {
			// expected
		}
	}
