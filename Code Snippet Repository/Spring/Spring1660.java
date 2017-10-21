	@Test
	public void withExplicitValidationMode() throws Exception {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(bf);
		reader.setValidationMode(XmlBeanDefinitionReader.VALIDATION_XSD);
		try {
			reader.loadBeanDefinitions(new ClassPathResource("invalidPerSchema.xml", getClass()));
			fail("Should not be able to parse a file with errors");
		}
		catch (BeansException ex) {
			assertTrue(ex.getCause() instanceof SAXParseException);
		}
	}
