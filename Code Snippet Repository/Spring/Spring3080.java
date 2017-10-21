	@Test
	public void testCustomKeyGenerator() {
		Object param = new Object();
		Object r1 = this.cs.customKeyGenerator(param);
		assertSame(r1, this.cs.customKeyGenerator(param));
		Cache cache = this.cm.getCache("testCache");
		// Checks that the custom keyGenerator was used
		Object expectedKey = SomeCustomKeyGenerator.generateKey("customKeyGenerator", param);
		assertNotNull(cache.get(expectedKey));
	}
