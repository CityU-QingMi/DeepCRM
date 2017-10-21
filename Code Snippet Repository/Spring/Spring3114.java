	@Test
	public void unknownCacheResolver() {
		try {
			this.simpleService.unknownCacheResolver(new Object());
			fail("Should have failed, no cache resolver with that name");
		}
		catch (NoSuchBeanDefinitionException ex) {
			assertEquals("Wrong bean name in exception", "unknownCacheResolver", ex.getBeanName());
		}
	}
