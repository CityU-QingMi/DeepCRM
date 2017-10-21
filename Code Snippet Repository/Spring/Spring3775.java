	@Test
	public void testLookupWithProxyInterfaceAndDefaultObject() throws Exception {
		JndiObjectFactoryBean jof = new JndiObjectFactoryBean();
		TestBean tb = new TestBean();
		jof.setJndiTemplate(new ExpectedLookupTemplate("foo", tb));
		jof.setJndiName("myFoo");
		jof.setProxyInterface(ITestBean.class);
		jof.setDefaultObject(Boolean.TRUE);
		try {
			jof.afterPropertiesSet();
			fail("Should have thrown IllegalArgumentException");
		}
		catch (IllegalArgumentException ex) {
			// expected
		}
	}
