	@Test
	public void testLookupWithDefaultObjectAndExpectedTypeNoMatch() throws Exception {
		JndiObjectFactoryBean jof = new JndiObjectFactoryBean();
		jof.setJndiTemplate(new ExpectedLookupTemplate("foo", ""));
		jof.setJndiName("myFoo");
		jof.setExpectedType(Boolean.class);
		jof.setDefaultObject("5");
		try {
			jof.afterPropertiesSet();
			fail("Should have thrown IllegalArgumentException");
		}
		catch (IllegalArgumentException ex) {
			// expected
		}
	}
