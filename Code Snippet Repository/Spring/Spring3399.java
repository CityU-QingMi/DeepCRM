	@Test
	public void privateMethodOnCglibProxyFails() throws Exception {
		try {
			load(CglibProxyWithPrivateMethod.class);
			fail("Should have thrown BeanInitializationException");
		}
		catch (BeanInitializationException ex) {
			assertTrue(ex.getCause() instanceof IllegalStateException);
		}
	}
