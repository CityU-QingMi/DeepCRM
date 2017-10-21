	@Test
	public void testUnknownCustomKeyGenerator() {
		try {
			Object param = new Object();
			this.cs.unknownCustomKeyGenerator(param);
			fail("should have failed with NoSuchBeanDefinitionException");
		}
		catch (NoSuchBeanDefinitionException ex) {
			// expected
		}
	}
