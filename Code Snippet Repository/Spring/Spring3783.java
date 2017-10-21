	@Test
	public void testLookupWithProxyInterfaceAndExpectedTypeAndNoMatch() {
		JndiObjectFactoryBean jof = new JndiObjectFactoryBean();
		TestBean tb = new TestBean();
		jof.setJndiTemplate(new ExpectedLookupTemplate("foo", tb));
		jof.setJndiName("foo");
		jof.setExpectedType(DerivedTestBean.class);
		jof.setProxyInterface(ITestBean.class);
		try {
			jof.afterPropertiesSet();
			fail("Should have thrown NamingException");
		}
		catch (NamingException ex) {
			assertTrue(ex.getMessage().indexOf("org.springframework.tests.sample.beans.DerivedTestBean") != -1);
		}
	}
