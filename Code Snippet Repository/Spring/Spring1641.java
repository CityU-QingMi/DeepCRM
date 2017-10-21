	@Test
	public void testFactoryMethodForJavaMailSession() {
		DefaultListableBeanFactory xbf = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(xbf);
		reader.loadBeanDefinitions(new ClassPathResource("factory-methods.xml", getClass()));

		MailSession session = (MailSession) xbf.getBean("javaMailSession");
		assertEquals("someuser", session.getProperty("mail.smtp.user"));
		assertEquals("somepw", session.getProperty("mail.smtp.password"));
	}
