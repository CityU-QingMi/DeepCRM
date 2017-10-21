	@Test
	public void verifyContentsOfHybridApplicationContext() {
		assertEquals("XML", fooFromXml);
		assertEquals("Java", fooFromJava);

		// Note: the XML bean definition for "enigma" always wins since
		// ConfigurationClassBeanDefinitionReader.isOverriddenByExistingDefinition()
		// lets XML bean definitions override those "discovered" later via an
		// @Bean method.
		assertEquals("enigma from XML", enigma);
	}
