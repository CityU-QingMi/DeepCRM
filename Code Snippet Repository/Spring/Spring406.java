	@Test
	public void testMatchingOnly() {
		// Can't do exact matching through isMatch
		assertTrue(pc.isMatch("echo", "ech*"));
		assertTrue(pc.isMatch("setName", "setN*"));
		assertTrue(pc.isMatch("setName", "set*"));
		assertFalse(pc.isMatch("getName", "set*"));
		assertFalse(pc.isMatch("setName", "set"));
		assertTrue(pc.isMatch("testing", "*ing"));
	}
