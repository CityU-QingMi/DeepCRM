	@Test
	public void testUnknownCustomCacheManager() {
		try {
			Object param = new Object();
			this.cs.unknownCustomCacheManager(param);
			fail("should have failed with NoSuchBeanDefinitionException");
		}
		catch (NoSuchBeanDefinitionException ex) {
			// expected
		}
	}
