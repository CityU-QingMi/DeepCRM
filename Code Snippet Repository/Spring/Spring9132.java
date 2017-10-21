	@Test
	public void testNoTransactionAttributeSource() {
		try {
			DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
			new XmlBeanDefinitionReader(bf).loadBeanDefinitions(new ClassPathResource("noTransactionAttributeSource.xml", getClass()));
			bf.getBean("noTransactionAttributeSource");
			fail("Should require TransactionAttributeSource to be set");
		}
		catch (FatalBeanException ex) {
			// Ok
		}
	}
