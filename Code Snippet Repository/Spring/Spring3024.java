	@Test
	public void testProxyingDecoratorNoInstance() throws Exception {
		String[] beanNames = this.beanFactory.getBeanNamesForType(ApplicationListener.class);
		assertTrue(Arrays.asList(beanNames).contains("debuggingTestBeanNoInstance"));
		assertEquals(ApplicationListener.class, this.beanFactory.getType("debuggingTestBeanNoInstance"));
		try {
			this.beanFactory.getBean("debuggingTestBeanNoInstance");
			fail("Should have thrown BeanCreationException");
		}
		catch (BeanCreationException ex) {
			assertTrue(ex.getRootCause() instanceof BeanInstantiationException);
		}
	}
