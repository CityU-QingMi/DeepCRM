	@Test
	public void simpleCircularImportIsDetected() throws Exception {
		boolean threw = false;
		try {
			newParser().parse(loadAsConfigurationSource(A.class), "A");
		}
		catch (BeanDefinitionParsingException ex) {
			assertTrue("Wrong message. Got: " + ex.getMessage(),
					ex.getMessage().contains(
						"Illegal attempt by @Configuration class 'AbstractCircularImportDetectionTests.B' " +
						"to import class 'AbstractCircularImportDetectionTests.A'"));
			threw = true;
		}
		assertTrue(threw);
	}
