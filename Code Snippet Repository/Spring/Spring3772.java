	@Test
	public void testLookupWithDefaultObjectAndExpectedTypeConversionViaBeanFactory() throws Exception {
		JndiObjectFactoryBean jof = new JndiObjectFactoryBean();
		jof.setJndiTemplate(new ExpectedLookupTemplate("foo", ""));
		jof.setJndiName("myFoo");
		jof.setExpectedType(Integer.class);
		jof.setDefaultObject("5");
		jof.setBeanFactory(new DefaultListableBeanFactory());
		jof.afterPropertiesSet();
		assertEquals(new Integer(5), jof.getObject());
	}
