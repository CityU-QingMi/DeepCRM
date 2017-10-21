	@Test
	public void keyAndKeyGeneratorCannotBeSetTogether() {
		try {
			new GenericXmlApplicationContext("/org/springframework/cache/config/cache-advice-invalid.xml");
			fail("Should have failed to load context, one advise define both a key and a key generator");
		}
		catch (BeanDefinitionStoreException ex) {
			// TODO better exception handling
		}
	}
