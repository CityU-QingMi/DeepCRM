	@Test
	public void complexCircularImportIsDetected() throws Exception {
		boolean threw = false;
		try {
			newParser().parse(loadAsConfigurationSource(X.class), "X");
		}
		catch (BeanDefinitionParsingException ex) {
			assertTrue("Wrong message. Got: " + ex.getMessage(),
					ex.getMessage().contains(
						"Illegal attempt by @Configuration class 'AbstractCircularImportDetectionTests.Z2' " +
						"to import class 'AbstractCircularImportDetectionTests.Z'"));
			threw = true;
		}
		assertTrue(threw);
	}
